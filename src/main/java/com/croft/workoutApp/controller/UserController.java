package com.croft.workoutApp.controller;

import com.croft.workoutApp.model.User;
import com.croft.workoutApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @GetMapping(value="/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping(value="/process_register")
    public String processRegister(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);

        return "register_success";
    }

    @GetMapping(value="/login")
    public String login() {

            return "login";
    }

    @PostMapping(value="/login")
    public String loggingIn(@RequestParam String email, @RequestParam String password, HttpSession session) {

        String encodedPassword = passwordEncoder.encode(password);

        Optional<User> dbUser = userRepo.findByEmail(email);
        String dbPass = userRepo.findByPassword(password).get().getPassword();
        Boolean match = passwordEncoder.matches(password, dbPass);
        System.out.println("password matches" + match);

        Optional<User> dbUserAndPass = userRepo.findByEmailAndPassword(email, password);

        System.out.println("user" + dbUser.isPresent());
        System.out.println("user and pass" + dbUserAndPass.isPresent());


        System.out.println(password);
        System.out.println(encodedPassword);
        System.out.println(dbUser.get().getPassword());

        if (dbUserAndPass.isPresent()) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, encodedPassword, auth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authRequest);
            auth.setAuthenticated(true);
            session.setAttribute("seven", "seven");
            session.setAttribute("loggedUserId", dbUser.get().getId());
            session.setAttribute("loggedUserFirstName", dbUser.get().getFirstName());
            session.setMaxInactiveInterval(30);
            System.out.println("This ran");
            System.out.println(auth);
            return "redirect:/dashboard";
        }
        System.out.println("This DID NOT ran");
        return "redirect:/login?logout";
    }


    @GetMapping("/dashboard")
    public String listUsers(Model model, RedirectAttributes redirect, HttpSession session) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        System.out.println("Hey dashboard");
        session.setAttribute("seven", "seven");


        return "dashboard";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
