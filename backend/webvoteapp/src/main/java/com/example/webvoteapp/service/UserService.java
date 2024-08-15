package com.example.webvoteapp.service;

import com.example.webvoteapp.model.User;
import com.example.webvoteapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }


}
