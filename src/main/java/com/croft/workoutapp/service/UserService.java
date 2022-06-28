package com.croft.workoutapp.service;

import com.croft.workoutapp.exception.EmailNotUniqueException;
import com.croft.workoutapp.model.User;
import com.croft.workoutapp.model.UserForm;
import com.croft.workoutapp.model.UserUpdateForm;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<User> getAllUsers();

    Optional<User> getUserById(long id);

    void createUser(User user);

    void updateUser(long id,  UserUpdateForm user);

    void deleteUser(long id);

    Optional<User> findByEmail(String email);

    void createUserFromForm(UserForm userForm) throws EmailNotUniqueException;



}
