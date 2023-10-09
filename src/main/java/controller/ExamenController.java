package controller;


import exception.ExamNotFoundException;
import model.Examen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.ExamenRepository;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ExamenController {
    @Autowired
    private ExamenRepository er;

    @PostMapping("/addExam")
    private Examen newTeacher(@RequestBody Examen newExamen) {
        return er.save(newExamen);
    }

    @GetMapping("/getExam")
    private List<Examen> getAllExams(){
        return er.findAll();
    }

    @GetMapping("/getExamById/{id}")
    private Examen getExamById(@PathVariable int id) {

        return er.findById(id)
                .orElseThrow(()->new ExamNotFoundException(id));

    }

    @PutMapping("/updateExam/{id}")
    private Examen updateExam(@RequestBody Examen newExam, @PathVariable  int id) {
        er.updateExamen( id, newExam.getDate(), newExam.getNom());

        // Retrieve the updated Examen (optional)
        Examen updatedExamen = er.findById( id)
                .orElseThrow(() -> new RuntimeException("Examen not found"));

        return updatedExamen;


    }

    @DeleteMapping("/deleteExam/{id}")
    private String deleteExam(@PathVariable int id) {
        if(!er.existsById(id)) {
            throw new ExamNotFoundException(id);
        }else {er.deleteById(id);
            return "Exam with id "+id+" Has Deleted";
        }
    }

}
