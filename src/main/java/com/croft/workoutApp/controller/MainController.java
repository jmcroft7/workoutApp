package com.croft.workoutApp.controller;

import com.croft.workoutApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String viewHomePage(Authentication authentication, HttpSession session) {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            String userEmail = ((UserDetails)principal).getUsername();
//            System.out.println("You are logged in with " + userEmail);
//            User user1 = userRepository.findByEmail(userEmail).get();
//            session.setAttribute("loggedUserId", user1.getId());
//            session.setAttribute("loggedUserFirstName", user1.getFirstName());
//            session.setMaxInactiveInterval(60);
//        } else {
//            System.out.println("You are not logged in and are Anonymous");
//        }

        return "index";
    }

    @GetMapping("/exercises")
    public String exercises(HttpSession session, RedirectAttributes redirect) {
        System.out.println(session);
        if (session.getAttribute("loggedUserId") == null) {
            redirect.addFlashAttribute("NotAuth", "You are not Authorized!");
            return "redirect:/login";
        }
        System.out.println(session.getAttribute("loggedUserId").toString());
        return "exercises";
    }
}
