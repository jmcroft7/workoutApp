package com.croft.workoutApp.serviceIMPL;

import com.croft.workoutApp.exception.EmailNotUniqueException;
import com.croft.workoutApp.model.User;
import com.croft.workoutApp.model.UserForm;
import com.croft.workoutApp.repository.UserRepository;
import com.croft.workoutApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
    @Override
    public void createUserFromForm(UserForm userForm) throws EmailNotUniqueException {

        if (userRepo.findByEmail(userForm.getEmail()).isPresent()) {
            throw new EmailNotUniqueException("Email already exists, try an email other than " + userForm.getEmail());

        }
        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());

        userRepo.save(user);

        }
    }
