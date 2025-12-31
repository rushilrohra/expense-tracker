package com.sgt.expense_tracker.service;

import com.sgt.expense_tracker.exceptions.EmailAlreadyExistsException;
import com.sgt.expense_tracker.exceptions.InvalidEmailException;
import com.sgt.expense_tracker.exceptions.UsernameAlreadyExistsException;
import com.sgt.expense_tracker.model.User;
import com.sgt.expense_tracker.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    //        check if valid email
//        check if email exists
//        check if username exists
//        hash password
//        If all pass, call repo
    public void register(User user) throws InvalidEmailException,EmailAlreadyExistsException,UsernameAlreadyExistsException{
        boolean isValidYN = isValidEmail(user.getEmail());
        boolean doesEmailExistYN = authRepository.emailAlreadyExists(user.getEmail());
        boolean doesUsernameExistYN = authRepository.usernameAlreadyExists(user.getUsername());
        if(isValidYN && !doesEmailExistYN && !doesUsernameExistYN){
            authRepository.addUser(
                    user.getName(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getMobile()
            );
        }
        else{
            if(!isValidYN){
                throw new InvalidEmailException();
            }
            if(doesEmailExistYN){
                throw new EmailAlreadyExistsException();
            }
            if (doesUsernameExistYN){
                throw new UsernameAlreadyExistsException();
            }
        }
    }

    public boolean isValidEmail(String email){
        if(email == null){
            return false;
        }
        String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
