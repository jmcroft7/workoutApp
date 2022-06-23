package com.croft.workoutApp.serviceIMPL;

import com.croft.workoutApp.exception.EmailNotUniqueException;
import com.croft.workoutApp.model.User;
import com.croft.workoutApp.model.UserForm;
import com.croft.workoutApp.model.UserUpdateForm;
import com.croft.workoutApp.repository.UserRepository;
import com.croft.workoutApp.service.UserService;
import com.croft.workoutApp.utils.RegisterUtil;
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
    public void updateUser(long id, UserUpdateForm user) {

        Optional<User> userData = userRepo.findById(id);

        String first = RegisterUtil.formatName(user.getFirstName());
        String last = RegisterUtil.formatName(user.getLastName());

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setEmail(user.getEmail());
            _user.setFirstName(first);
            _user.setLastName(last);
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
    public void createUserFromForm(UserForm userForm) throws EmailNotUniqueException {

        if (userRepo.findByEmail(userForm.getEmail()).isPresent()) {
            throw new EmailNotUniqueException("Email already exists, try an email other than " + userForm.getEmail());
        }

        String first = RegisterUtil.formatName(userForm.getFirstName());
        String last = RegisterUtil.formatName(userForm.getLastName());

        User user = new User();
        user.setFirstName(first);
        user.setLastName(last);
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());

        userRepo.save(user);
        }
    }
