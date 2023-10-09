package controller;


import exception.TeacherNotFoundException;
import model.Teacher;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.TeacherRepository;
import repository.UserRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherRepository tr;
    @Autowired
    private UserRepository ur;

    public TeacherController(UserRepository ur) {
        this.ur = ur;
    }

    @PostMapping("/addTeacher")
    private Teacher newTeacher(@RequestBody Teacher newTeacher) {


        User user =new User();
        user.setRole("Teacher");
        user.setEmail(newTeacher.getEmail());
        user.setFirstName(newTeacher.getName());
        user.setPassword(newTeacher.getName());
        ur.save(user);

        return tr.save(newTeacher);

    }

    @GetMapping("/getTeacher")
    private List<Teacher> getAllTeacher(){
        return tr.findAll();
    }

    @GetMapping("/getTeacherById/{id}")
    private Teacher getTeacherById(@PathVariable int id) {
        return tr.findById(id)
                .orElseThrow(()->new TeacherNotFoundException(id));
    }

    @PutMapping("/updateTeacher/{id}")
    private Teacher updateTeacher(@RequestBody Teacher newTeacher) {
        return tr.save(newTeacher);
    }

    @DeleteMapping("/deleteTeacher/{id}")
    private String deleteTeacher(@PathVariable int id) {
        if(!tr.existsById(id)) {
            throw new TeacherNotFoundException(id);
        }else {
            tr.deleteById(id);
            return "Teacher with id "+id+" Has Deleted";
        }
    }






}
