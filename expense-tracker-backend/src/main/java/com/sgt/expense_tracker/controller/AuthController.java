package com.sgt.expense_tracker.controller;

import com.sgt.expense_tracker.exceptions.EmailAlreadyExistsException;
import com.sgt.expense_tracker.exceptions.InvalidEmailException;
import com.sgt.expense_tracker.exceptions.UsernameAlreadyExistsException;
import com.sgt.expense_tracker.model.User;
import com.sgt.expense_tracker.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin(origins = "*")

public class AuthController {
    @Autowired
    AuthService authService;

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody User user){
        try{
            authService.register(user);
            return ResponseEntity.ok().body(Map.of("Value","Inserted Successfully"));
        }
        catch (EmailAlreadyExistsException | UsernameAlreadyExistsException | InvalidEmailException e){
            return ResponseEntity.badRequest().body(Map.of("Value",e.getMessage()));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String,String>> forgotPwd(@RequestBody Map<String,String> body){
        try {
            authService.forgotPwd(body);
            return ResponseEntity.ok().body(Map.of("Body","Success"));

        } catch (InvalidEmailException e) {
            logger.info(String.valueOf(e));
            return ResponseEntity.badRequest().body(Map.of("Body",e.getMessage()));

        }
    }

    @PostMapping("/validate-token")
    public void validateToken(@RequestBody Map<String,String> body){
        authService.validateToken(body);
    }

    @PutMapping("/reset-password")
    public void resetPassword(@RequestBody Map<String,String> body){
        authService.resetPassword(body);
    }
}
