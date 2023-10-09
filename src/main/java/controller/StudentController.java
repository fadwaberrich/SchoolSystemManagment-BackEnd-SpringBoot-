package controller;

import java.util.List;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import exception.StudentNotFoundException;

import model.Student;


import repository.StudentRepository;
import repository.UserRepository;


@RestController
@CrossOrigin("*")
public class StudentController {


	@Autowired
	private StudentRepository sr;
	private UserRepository ur;

	public StudentController(UserRepository ur) {
		this.ur = ur;
	}


	@PostMapping("/addStudent")
	private Student newStudent(@RequestBody Student newStudent) {
		User user =new User();
		user.setRole("student");
		ur.save(user);


		return sr.save(newStudent);

	}
	
	@GetMapping("/getStudent")
	private List<Student> getAllStudent(){
		return sr.findAll();
	}
	
	@GetMapping("/getStudentById/{id}")
	private Student getStudentById(@PathVariable int id) {
		return sr.findById(id)
				.orElseThrow(()->new StudentNotFoundException(id));
	}
	
	@PutMapping("/updateStudent/{id}")
	private Student updateStudent(@RequestBody Student newStudent) {
		return sr.save(newStudent);
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	private String deleteStudent(@PathVariable int id) {
		if(!sr.existsById(id)) {
			throw new StudentNotFoundException(id);
		}else {
			sr.deleteById(id);
			return "Student with id "+id+" Has Deleted";
		}
	}
	
	


}

















