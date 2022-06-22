package com.croft.workoutApp.controller;

import com.croft.workoutApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserLoginController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String login(HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("loggedInUserId") != null) {
            redirectAttributes.addFlashAttribute("alreadyLogged", "You are already logged in!");
            return "redirect:/error/500";
        }
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session, Authentication authentication) {
//        invalidate session and set authenticated to false
        session.invalidate();
        authentication.isAuthenticated();
        log.info("successful logout");
        return "redirect:/";
    }

}

//
//    @PostMapping(value="/login")
//    public String loggingIn(@RequestParam String email, @RequestParam String password, HttpSession session) {
//
//        String encodedPassword = passwordEncoder.encode(password);
//
//        Optional<User> dbUser = userRepo.findByEmail(email);
//        String dbPass = userRepo.findByPassword(password).get().getPassword();
//        Boolean match = passwordEncoder.matches(password, dbPass);
//        System.out.println("password matches" + match);
//
//        Optional<User> dbUserAndPass = userRepo.findByEmailAndPassword(email, password);
//
//        System.out.println("user" + dbUser.isPresent());
//        System.out.println("user and pass" + dbUserAndPass.isPresent());
//
//
//        System.out.println(password);
//        System.out.println(encodedPassword);
//        System.out.println(dbUser.get().getPassword());
//
//        if (dbUserAndPass.isPresent()) {
//
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, encodedPassword, auth.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authRequest);
//            auth.setAuthenticated(true);
//            session.setAttribute("seven", "seven");
//            session.setAttribute("loggedUserId", dbUser.get().getId());
//            session.setAttribute("loggedUserFirstName", dbUser.get().getFirstName());
//            session.setMaxInactiveInterval(30);
//            System.out.println("This ran");
//            System.out.println(auth);
//            return "redirect:/dashboard";
//        }
//        System.out.println("This DID NOT ran");
//        return "redirect:/login?logout";
//    }
//
//
//    @GetMapping("/dashboard")
//    public String listUsers(Model model, RedirectAttributes redirect, HttpSession session) {
//        List<User> listUsers = userRepo.findAll();
//        model.addAttribute("listUsers", listUsers);
//        System.out.println("Hey dashboard");
//        session.setAttribute("seven", "seven");
//
//
//        return "dashboard";
