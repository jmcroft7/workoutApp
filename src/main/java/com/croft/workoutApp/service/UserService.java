package com.croft.workoutApp.service;

import com.croft.workoutApp.exception.EmailNotUniqueException;
import com.croft.workoutApp.model.User;
import com.croft.workoutApp.model.UserForm;
import com.croft.workoutApp.model.UserUpdateForm;
import com.croft.workoutApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

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
