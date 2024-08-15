package com.example.webvoteapp.repository;

import com.example.webvoteapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount(String account);
}
