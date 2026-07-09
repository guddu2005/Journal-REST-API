package com.example.myFirstProject.Controller;


import com.example.myFirstProject.Service.UserService;
import com.example.myFirstProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService = new UserService();
    @GetMapping("health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("create-user")
    public  void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
