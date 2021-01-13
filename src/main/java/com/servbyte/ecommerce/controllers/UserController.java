package com.servbyte.ecommerce.controllers;

import com.servbyte.ecommerce.dtos.ApplicationUserDto;
import com.servbyte.ecommerce.dtos.ApplicationUserLoginDto;
import com.servbyte.ecommerce.service.UserService;
import com.servbyte.ecommerce.utility.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody ApplicationUserDto userDTO){
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping
    public ResponseEntity<?> fetchAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/auth")
    public ResponseEntity<?> generateToken(@RequestBody ApplicationUserLoginDto loginDTO) {
        return ResponseEntity.ok(userService.generateToken(loginDTO));
    }


    @GetMapping("/loggedUser")
    public ResponseEntity<?>  getLoggedInUser() {
        return ResponseEntity.ok(AuthenticatedUser.getLoggedInUser());
    }

    @GetMapping("/roles")
    public ResponseEntity<?>  fetchAllRoles() {
        return ResponseEntity.ok(userService.fetchAllRoles());
    }
}
