package com.sgt.expense_tracker.controller;

import com.sgt.expense_tracker.exceptions.EmailAlreadyExistsException;
import com.sgt.expense_tracker.exceptions.InvalidEmailException;
import com.sgt.expense_tracker.exceptions.UsernameAlreadyExistsException;
import com.sgt.expense_tracker.model.User;
import com.sgt.expense_tracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")

public class AuthController {
    @Autowired
    AuthService authService;

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

}
