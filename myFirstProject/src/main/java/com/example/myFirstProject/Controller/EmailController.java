package com.example.myFirstProject.Controller;


import com.example.myFirstProject.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public ResponseEntity<String> sendMail() {

        emailService.sendEmail(
                "reachguddu.dev@gmail.com",
                "Welcome",
                "Hello! This is a test email from Spring Boot."
        );

        return ResponseEntity.ok("Email sent successfully");
    }
}
