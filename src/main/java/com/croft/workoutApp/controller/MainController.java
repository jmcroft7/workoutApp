package com.croft.workoutApp.controller;

import com.croft.workoutApp.model.User;
import com.croft.workoutApp.repository.CustomUserDetails;
import com.croft.workoutApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String viewHomePage() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            System.out.println("This is first IF " + username);
            User user1 = userRepository.findByEmail(username);
            System.out.println("email= " + user1.getEmail() + " name= " + user1.getFirstName() + " id= " +user1.getId());
        } else {
            String username = principal.toString();
            System.out.println("This is first ELSE " + username);
        }
        return "index";
    }


    @GetMapping("/exercises")
    public String exercises() {
        return "exercises";
    }
}
