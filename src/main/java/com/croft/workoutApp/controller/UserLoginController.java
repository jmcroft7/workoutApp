package com.croft.workoutApp.controller;

import com.croft.workoutApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserLoginController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String login() {
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

    //    @PostMapping(value = "/loginSubmit")
//    public String loginSubmit(HttpSession session, @RequestParam String email, @RequestParam String password, Model model) throws Exception {
////        custom authentication through spring
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String loggedUserEmail = authentication.getName();
//        User user = userService.findByEmail(loggedUserEmail).get();
//
//        if(user != null){
//            log.info(user.toString());
//            model.addAttribute("loggedInUserId", user.getId());
//            session.setAttribute("loggedInId", user.getId());
//            session.setAttribute("loggedInName", user.getFirstName());
//            log.info(authentication.toString());
//            log.info(session.getAttributeNames().toString());
//            log.info("successful login");
//            return "redirect:/";
//        } else {
//            log.info("unsuccessful login attempt");
//            return "redirect:/home";
//        }
//    }

}
