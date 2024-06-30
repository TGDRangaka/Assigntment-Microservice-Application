package lk.ijse.userservice.controller;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/health")
    public String health() {
        return "User Good!";
    }

//    Registration
    @PostMapping
    public UserDTO registerUser(@RequestBody UserDTO user){
        return userService.register(user);
    }

//    Profile Update
    @PutMapping("/{userId}")
    public String updateUser(@RequestBody UserDTO user, @PathVariable String userId){
        String update = userService.update(user, userId);
        return update;
    }

//    Credentials Verification
    @PostMapping("/verify-user/{email}/{password}")
    public boolean loginUser(@PathVariable String email, @PathVariable String password){
        return userService.userVerify(email, password);
    }
}
