package com.croft.workoutApp.controller;

import com.croft.workoutApp.security.CustomUserDetails;
import com.croft.workoutApp.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@Slf4j
@Controller
@RequestMapping(value="home")
public class AppController {

    @GetMapping("")
    public String viewHomePage(HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("loggedInUserId") != null) {
            redirectAttributes.addFlashAttribute("alreadyLogged", "You are already logged in!");
            return "redirect:/home/dashboard";
        }
        return SessionUtil.setSession(session, redirectAttributes);

    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, RedirectAttributes redirectAttributes) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            log.error("You are not logged in!");
            redirectAttributes.addFlashAttribute("NotAuth", "You are not allowed here! Login to continue");
            return "redirect:/login";
        }
        return "dashboard";
    }
}
