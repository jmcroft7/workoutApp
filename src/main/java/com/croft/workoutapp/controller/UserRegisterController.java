package com.croft.workoutapp.controller;

import com.croft.workoutapp.exception.EmailNotUniqueException;
import com.croft.workoutapp.model.UserForm;
import com.croft.workoutapp.service.UserService;
import com.croft.workoutapp.utils.RegisterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class UserRegisterController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/register")
    public String register(@ModelAttribute("userForm") UserForm userForm, HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("Register Route Ran");

//                if user is logged in
        if (session.getAttribute("loggedInUserId") != null) {
            redirectAttributes.addFlashAttribute("alreadyLogged", "You are already logged in!");
            return "redirect:/error/500";
        }

        return "register";
    }

    @PostMapping(value = "/registerSubmit")
    public String registerSubmit(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) throws EmailNotUniqueException {
        log.info("RegisterSubmit Route Ran");

//        if user is logged in
        if (session.getAttribute("loggedInUserId") != null) {
            redirectAttributes.addFlashAttribute("alreadyLogged", "You are already logged in!");
            return "redirect:/error/500";
        }

//        if validation errors
        if (result.hasErrors()) {
            return "register";
        }

//        if passwords don't match
        if (!RegisterUtil.checkPass(userForm.getPassword(), userForm.getConfirm())) {
            redirectAttributes.addFlashAttribute("misMatch", "Passwords don't match!");
            return "redirect:/register";
        }

//        if email already exists
        if (userService.findByEmail(userForm.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("isNotUnique", "The email you entered already exists!");
            return "redirect:/register";
        }


//        create user from form
        userService.createUserFromForm(userForm);
        redirectAttributes.addFlashAttribute("registerSuccess", "You have successfully registered!");

        return "redirect:/login";
    }

}
