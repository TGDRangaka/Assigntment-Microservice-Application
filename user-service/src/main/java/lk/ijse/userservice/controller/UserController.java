package lk.ijse.userservice.controller;

import lk.ijse.userservice.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/health")
    public String health() {
        return "User Good!";
    }

//    Registration
    @PostMapping
    public UserDTO registerUser(@RequestBody UserDTO user){
        return user;
    }

//    Profile Update

//    Credentials Verification
}
