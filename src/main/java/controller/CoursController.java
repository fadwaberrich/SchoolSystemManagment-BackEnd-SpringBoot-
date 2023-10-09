package controller;

import dto.Message;
import exception.CourseNotFoundException;
import exception.TeacherNotFoundException;
import model.Cours;
import model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import repository.CoursRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin("*")

public class CoursController {

    @Autowired
    private WSService service;



    @Autowired
    private CoursRepository cr;
    //private static final String UPLOAD_DIR = "/uploads";
    @Autowired
    private org.springframework.mail.javamail.JavaMailSender JavaMailSender;
    @Autowired
    private static final String UPLOAD_DIR = "uploads/";


    @PostMapping("/addCours")

    private Cours newCours(@RequestPart MultipartFile courseFile, @RequestPart String name) {


        try {

            byte[] bytes = courseFile.getBytes();
            Path path = Paths.get(UPLOAD_DIR + courseFile.getOriginalFilename());
            Files.write(path, bytes);

            Cours cours=new Cours("http://localhost:8084/content/"+courseFile.getOriginalFilename().replaceAll("\\s", "%20"),name);
            Cours savedCours =cr.save(cours);
            // Send an email notification
            sendEmailNotification(savedCours);
            // Envoyer une notification WebSocket
            //messagingTemplate.convertAndSend("/topic/coursNotifications", "Nouveau cours créé : " + name);
            Message message=new Message();
            message.setMessageContent("cours"+ cours.getName()+ "added");
            service.notifyFrontend(message.getMessageContent());
            return savedCours;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw an exception as needed
        }
    }

    private void sendEmailNotification(Cours cours) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("fadwa.berrich@esprit.tn"); // Replace with the recipient's email address
        message.setSubject("New Course Created");
        message.setText("A new course named '" + cours.getName() + "' has been created.");

        JavaMailSender.send(message);
    }




    @GetMapping("/getCours")
    private List<Cours> getAllCourses(){
        return cr.findAll();
    }
    @GetMapping("/getCourseById/{id}")
    private Cours getCourserById(@PathVariable int id) {
        return cr.findById(id)
                .orElseThrow(()->new CourseNotFoundException(id));
    }

   @PutMapping("/updateCourse/{id}")
    private Cours updateCours(@RequestBody Cours newCours,@RequestPart MultipartFile courseFile, @RequestPart String name) {
        return cr.save(newCours(courseFile,name));
    }
  /*  @PutMapping("/updateCourse/{id}")
    @Transactional
    public Cours updateCoursById(int coursId, String newName, MultipartFile updatedCourseFile) throws IOException {
        // Retrieve the Cours object by its ID
        Cours existingCours = cr.findById(coursId).orElse(null);

        if (existingCours != null) {
            // Update the name of the Cours object
            existingCours.setName(newName);

            // Update the courseFile if a new file is provided
            if (updatedCourseFile != null && !updatedCourseFile.isEmpty()) {
                byte[] bytes = updatedCourseFile.getBytes();
                Path path = Paths.get(UPLOAD_DIR + updatedCourseFile.getOriginalFilename());
                Files.write(path, bytes);

                existingCours.setCourseFile("http://localhost:8084/content/" + updatedCourseFile.getOriginalFilename().replaceAll("\\s", "%20"));
            }

            // Save the updated Cours object back to the database
            Cours updatedCours = cr.save(existingCours);

            return updatedCours;
        } else {
            // Handle the case where the Cours with the given ID does not exist
            return null;
        }
    }*/

    @DeleteMapping("/deleteCourse/{id}")
    private String deleteCourse(@PathVariable int id) {
        if(!cr.existsById(id)) {
            throw new CourseNotFoundException(id);
        }else {
           cr.deleteById(id);
            return "Course with id "+id+" Has been Deleted";
        }




}}
