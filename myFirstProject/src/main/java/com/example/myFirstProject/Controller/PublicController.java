package com.example.myFirstProject.Controller;


import com.example.myFirstProject.Service.UserService;
import com.example.myFirstProject.entity.User;
import com.example.myFirstProject.util.JWTUtil;
import io.jsonwebtoken.JwtBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService = new UserService();
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/signup")
    public  void signup(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt , HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.error("Exception during Login" ,e);
            return new ResponseEntity<>("Incorrect Password." ,HttpStatus.BAD_REQUEST);
        }
    }
}
