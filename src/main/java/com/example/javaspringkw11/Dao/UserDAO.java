package com.example.javaspringkw11.Dao;

import com.example.javaspringkw11.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDAO extends JpaRepository<User,Integer> {

    UserDetails findByUsername(String username);
}
