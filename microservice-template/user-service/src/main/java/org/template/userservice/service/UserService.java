package org.template.userservice.service;


import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.template.userservice.client.SecurityServiceClient;
import org.template.userservice.exception.ApplicationException;
import org.template.userservice.exception.Failures;
import org.template.userservice.model.JwtAuthenticationResponse;
import org.template.userservice.model.LoginRequest;
import org.template.userservice.model.UserDetails;
import org.template.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    private SecurityServiceClient securityServiceClient;


    private final Failures failures = new Failures();


    public List<UserDetails> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<?> getUser(Long id){
        try {
            var userDetails = new UserDetails()
                    .setUserName("u")
                    .s
            Optional<UserDetails> userDetails =userRepository.findById(id);
            logger.info(userDetails.toString());
          //  System.out.println(userDetails.toString());
            if (userDetails.isEmpty()){
                return Optional.of(ResponseEntity.badRequest().body("User does not exist"));
            }
            return userDetails;
        }catch (Exception e){
            logger.info("Error fetching users {}",e.getMessage());

            throw new RuntimeException(e.getMessage());
        }
    }


    public JwtAuthenticationResponse login(LoginRequest loginRequest){
        try{
            JwtAuthenticationResponse response = securityServiceClient.login(loginRequest);
            System.out.println(response);
            return response;

        }  catch (Exception e) {
            logger.info("Login failed : {}", e.getMessage());
            throw new ApplicationException("LOGIN_FAILED : Invalid credentials", e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    public String test(){
        try {
            System.out.println("TESTeddddddddddd ");
            return securityServiceClient.test();

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
