package com.croft.workoutApp.controller;

import com.croft.workoutApp.model.User;
import com.croft.workoutApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/account")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/{id}")
    public String userAccount(@PathVariable("id") Integer id, HttpSession session) throws Exception {


        User user = userService.getUserById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserEmail = authentication.getName();
        User currentUser = userService.findByEmail(loggedUserEmail).get();

        if (currentUser != null && user.getId().equals(currentUser.getId())) {
            log.info(String.valueOf(user));

            return "profile";
        }
        return "redirect:/error/404";
    }

//    @PostMapping(value = "/account/saved")
//    public ModelAndView userAccountEdit(@Valid AccountFormBean accountFormBean) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/account/");
//
//        User user = userDAO.findById(accountFormBean.getId());
//        userService.getUserDetails(accountFormBean, user);
//        userDAO.save(user);
//
//        response.setViewName("redirect:/user/account/" + user.getId());
//        return response;
//    }

}
