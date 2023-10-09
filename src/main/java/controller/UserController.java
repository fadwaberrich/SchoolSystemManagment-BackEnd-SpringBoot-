package controller;

import model.Teacher;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.TeacherRepository;
import repository.UserRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserRepository ur;

    @PostMapping("/addUser")
    private User newUser(@RequestBody User newUser) {
        return ur.save(newUser);
    }
    @GetMapping("/getUser")
    private List<User> getAllUser(){
        return ur.findAll();
    }


}
