package com.croft.workoutApp.controller;

import com.croft.workoutApp.model.User;
import com.croft.workoutApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String userEmail = ((UserDetails)principal).getUsername();
            System.out.println("You are logged in with " + userEmail);
            System.out.println("Id = " + session.getAttribute("loggedInId"));
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
    public String dashboard() {

        return "dashboard";
    }
}
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @GetMapping(value="/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//
//        return "register";
//    }
//
//    @PostMapping(value="/process_register")
//    public String processRegister(User user) {
//
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userRepo.save(user);
//
//        return "register_success";
//    }
//
//    @GetMapping(value="/login")
//    public String login() {
//
//            return "login";
//    }
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
