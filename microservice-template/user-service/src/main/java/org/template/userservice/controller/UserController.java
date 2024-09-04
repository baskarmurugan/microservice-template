package org.template.userservice.controller;

import org.apache.commons.io.output.ClosedOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.template.userservice.model.JwtAuthenticationResponse;
import org.template.userservice.model.LoginRequest;
import org.template.userservice.model.UserDetails;
import org.template.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("getAllUser")
    public List<UserDetails> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<?>> getUser(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(userService.getUser(id));
        }catch (Exception e){
            logger.info("Error fetching user : {}",e.getMessage());
            throw  new RuntimeException(e.getMessage());
        }

    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            return userService.login(loginRequest);
        }catch (Exception e){
            logger.info("Exception occurred at login : {}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/test")
    public String test() {
        try {
            logger.info("calling test ");
            System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
            return userService.test();
        }catch (Exception e){

            throw new RuntimeException(e.getMessage());
        }
    }

}
