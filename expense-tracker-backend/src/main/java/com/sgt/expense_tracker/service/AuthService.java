package com.sgt.expense_tracker.service;

import com.sgt.expense_tracker.exceptions.EmailAlreadyExistsException;
import com.sgt.expense_tracker.exceptions.InvalidEmailException;
import com.sgt.expense_tracker.exceptions.UsernameAlreadyExistsException;
import com.sgt.expense_tracker.model.User;
import com.sgt.expense_tracker.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    @Autowired
    EmailService emailService;

    //        check if valid email
//        check if email exists
//        check if username exists
//        hash password
//        If all pass, call repo
    public void register(User user) throws InvalidEmailException,EmailAlreadyExistsException,UsernameAlreadyExistsException{
        if(!isValidEmail(user.getEmail())){
            throw new InvalidEmailException();
        }
        if(authRepository.findByEmail(user.getEmail()) != null){
            throw new EmailAlreadyExistsException();
        }
        if (authRepository.findByUsername(user.getUsername()) != null){
            throw new UsernameAlreadyExistsException();
        }
        authRepository.addUser(
                user.getName(), user.getUsername(), user.getEmail(), hashPassword(user.getPassword()), user.getMobile()
        );
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

    public String hashPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public void forgotPwd(Map<String,String> body) throws InvalidEmailException {
        String email = body.get("email");
        User user = authRepository.findByEmail(email);

        if(user == null){
            throw new InvalidEmailException();
        }
        String token = UUID.randomUUID().toString();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(15);

        int res = authRepository.saveToken(token,expiry,user.getId());
        if(res == 1){
            String link = "http://localhost:4200/reset-pwd/" + token;
            emailService.sendEmail(email,link);
        }
    }

    public void validateToken(Map<String,String> body){
        String token = body.get("token");
        authRepository.validateToken(token);
    }

    public void resetPassword(Map<String,String> body){
        String token = body.get("token");
        String password = body.get("password");

        authRepository.resetPassword(token, password);
    }

}
