package com.croft.workoutApp.controller;

import com.croft.workoutApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
@Slf4j
@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String viewHomePage(HttpSession session) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String userEmail = ((UserDetails)principal).getUsername();
            System.out.println("You are logged in with " + userEmail);
            session.setAttribute("userEmail", userEmail);
            session.setAttribute("seven", "seven");
            System.out.println("Id = " + session.getAttribute("loggedInUserId"));
            System.out.println(SecurityContextHolder.getContext());
        } else {
            System.out.println("You are not logged in and are Anonymous");
        }

        return "index";
    }

    @GetMapping("/exercises")
    public String exercises() {

        return "exercises";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        session.setAttribute("loggedInUserId", "1");
        session.setMaxInactiveInterval(30);
        return "dashboard";
    }
}
