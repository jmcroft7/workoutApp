package com.croft.workoutApp.service;

import com.croft.workoutApp.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(long id);

    public void updateUser(long id);

    public void updateUserById();

}
