package com.croft.workoutApp.service;

import com.croft.workoutApp.exception.EmailNotUniqueException;
import com.croft.workoutApp.model.User;
import com.croft.workoutApp.model.UserForm;
import com.croft.workoutApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserService {


    public List<User> getAllUsers();

    public Optional<User> getUserById(long id);

    public void createUser(User user);

    public void updateUser(long id,  User user);

    public void deleteUser(long id);

    public Optional<User> findByEmail(String email);

    public void createUserFromForm(UserForm userForm) throws EmailNotUniqueException;



}
