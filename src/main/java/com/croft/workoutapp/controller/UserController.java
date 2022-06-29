package com.croft.workoutapp.controller;

import com.croft.workoutapp.model.UserUpdateForm;
import com.croft.workoutapp.service.UserService;
import com.croft.workoutapp.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/account")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public String userAccountInfo(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("Account Details Route Ran");

//                checks if user is authenticated and that the user id matches the route id
        return SessionUtil.checkSession(session, redirectAttributes, id, "profile");
    }

    @GetMapping(value = "/{id}/edit")
    public String editUserAccount(@PathVariable("id") Integer id, @ModelAttribute("userUpdateForm") UserUpdateForm userUpdateForm, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        log.info("Account Edit Route Ran");
//        checks if user is authenticated and that the user id matches the route id
        return SessionUtil.checkSession(session, redirectAttributes, id, "profileEdit");

    }

    @PostMapping(value = "/{id}/save")
    public String editUserAccountSubmit(@PathVariable("id") Integer id, @Valid @ModelAttribute("userUpdateForm") UserUpdateForm userUpdateForm, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("Account Update Route Ran");

//         checks if current user matches path variable
        if (!id.toString().equals(session.getAttribute("loggedInUserId").toString())) {
            redirectAttributes.addFlashAttribute("NotAuth", "This page either does not exist or you are not authorized");
            return "redirect:/error/404";
        }

//        if validation errors
        if (result.hasErrors()) {
            return "profileEdit";
        }

//        if email is the same as before
        if (userUpdateForm.getEmail().equals(session.getAttribute("loggedInUserEmail").toString())) {
            userService.updateUser(id, userUpdateForm);
            SessionUtil.updateSession(session, redirectAttributes, (long) id, userUpdateForm);
            return "redirect:/account/" + id;
        }

//        if email already exists
        if (userService.findByEmail(userUpdateForm.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("isNotUnique", "The email you entered already exists!");
            return "redirect:/account/" + id + "/edit";
        }

//        if email is different and unique, create user from form
        userService.updateUser(id, userUpdateForm);
        SessionUtil.updateSession(session, redirectAttributes, (long) id, userUpdateForm);
        return "redirect:/account/" + id;
    }

    @GetMapping(value = "/{id}/showDelete")
    public String showDeleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, HttpSession session) {
        return SessionUtil.checkSession(session, redirectAttributes, id, "delete_profile");
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteUserAccount(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, HttpSession session, Authentication authentication) {
        log.info("Account Delete Route Ran");

//         checks if current user matches path variable
        if (!id.toString().equals(session.getAttribute("loggedInUserId").toString())) {
            redirectAttributes.addFlashAttribute("NotAuth", "This page either does not exist or you are not authorized");
            return "redirect:/error/404";
        }

//        create user from form
        userService.deleteUser(id);
        session.invalidate();
        authentication.isAuthenticated();
        redirectAttributes.addFlashAttribute("successDelete","Your account has been deleted");
        return "redirect:/";
    }

}
