package com.example.javaspringkw11.Services;

import com.example.javaspringkw11.models.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findAll();

    User findOneById(int id);
}
