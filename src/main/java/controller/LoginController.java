package controller;


import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import dto.Login;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import Services.UserService;

@RestController
@CrossOrigin("*")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 UserService us;

    @PostMapping("/login")

    public ResponseEntity login(@RequestBody Login login ){
       User ExistedUser=userRepository.findByEmail(login.getEmail());

        if (ExistedUser != null)
        {
            if(login.getPassword().equals(ExistedUser.getPassword()))
            {
            return new ResponseEntity<User>(ExistedUser, HttpStatus.OK);

            }
            else {
                return new ResponseEntity<>("incorrect pwd", HttpStatus.OK);

            }
        }


            return new ResponseEntity<>("Login echoue", HttpStatus.OK);

    }
}
