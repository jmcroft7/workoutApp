package com.croft.workoutApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserLoginController {


    @GetMapping(value = "/login")
    public String login(HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("Login Route Ran");

//        if user is logged in
        if (session.getAttribute("loggedInUserId") != null) {
            redirectAttributes.addFlashAttribute("alreadyLogged", "You are already logged in!");
            return "redirect:/error/500";
        }

        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session, Authentication authentication) {
        log.info("Logout Route Ran");

//        invalidate session and set authenticated to false
        session.invalidate();
        authentication.isAuthenticated();
        return "redirect:/";
    }

}
