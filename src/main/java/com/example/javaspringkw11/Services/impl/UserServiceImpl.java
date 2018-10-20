package com.example.javaspringkw11.Services.impl;

import com.example.javaspringkw11.Dao.UserDAO;
import com.example.javaspringkw11.Services.UserService;
import com.example.javaspringkw11.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

@Autowired
private UserDAO userDAO;

    @Override
    public void save(User user) {
if (user!=null){
    userDAO.save(user);
}
    }


    @Override
    public List<User> findAll() {
        List<User> all = userDAO.findAll();
        if (all==null){
            return new ArrayList<>();
        }
        return all;
    }



    @Override
    public User findOneById(int id) {
        return null;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);
    }
}