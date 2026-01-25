package com.sgt.expense_tracker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
    public void sendMailTest(){
        emailService.sendEmail("panipuri0110@gmail.com", "123467890qwertyuiopasdfghjklzxcvbnm");
    }

}