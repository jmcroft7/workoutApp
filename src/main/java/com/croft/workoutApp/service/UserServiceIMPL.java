package com.croft.workoutApp.service;

import com.croft.workoutApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceIMPL implements UserService {

    @Autowired
    UserService userService;


    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public void updateUser(long id) {

    }

    @Override
    public void updateUserById() {

    }
}
