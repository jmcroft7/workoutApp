package com.croft.workoutApp.controller;

import com.croft.workoutApp.exception.EmailNotUniqueException;
import com.croft.workoutApp.model.UserForm;
import com.croft.workoutApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/register")
    public String register(@ModelAttribute("userForm") UserForm userForm) {
        log.info("Register route ran");

        return "register";
    }

    @PostMapping(value = "/registerSubmit")
    public String registerSubmit(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws EmailNotUniqueException {

        System.out.println(result.toString());
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.findByEmail(userForm.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("isNotUnique", "This email already exists!");
            return "redirect:/register";
        }

        userService.createUserFromForm(userForm);
        log.info("successful register");

        return "register_success";
    }

}
