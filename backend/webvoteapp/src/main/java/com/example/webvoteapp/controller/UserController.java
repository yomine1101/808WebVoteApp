package com.example.webvoteapp.controller;

import com.example.webvoteapp.model.User;
import com.example.webvoteapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String account = loginRequest.get("account");
        String password = loginRequest.get("password");

        User user = userService.findByAccount(account);
        Map<String, String> response = new HashMap<>();

        if (user != null && user.getPassword().equals(password)) {
            response.put("status", "success");
            response.put("role", user.getRole());
            response.put("username",user.getUsername());
        } else {
            response.put("status", "failure");
        }

        return response;
    }

}
