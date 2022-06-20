package com.croft.workoutApp.service;

import com.croft.workoutApp.model.User;
import com.croft.workoutApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<User>();
        userRepo.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Optional<User> getUserById(long id) {

        return userRepo.findById(id);
    }

    @Override
    public void createUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateUser(long id, User user) {

        Optional<User> userData = userRepo.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            userRepo.save(_user);
        }
    }

    @Override
    public void deleteUser(long id) {

        Optional<User> userData = userRepo.findById(id);

        if (userData.isPresent()) {
            userRepo.deleteById(id);
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public boolean isFoundByFirstNameAndId(String firstName, long id) {
        return false;
    }

}
