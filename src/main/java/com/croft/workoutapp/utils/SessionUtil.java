package com.croft.workoutapp.utils;

import com.croft.workoutapp.model.UserUpdateForm;
import com.croft.workoutapp.security.CustomUserDetails;
import com.croft.workoutapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
public class SessionUtil {

    UserService userService;

    public static String setSession(HttpSession session, RedirectAttributes redirectAttributes) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserDetails)) {
            log.error("You are not logged in!");
            redirectAttributes.addFlashAttribute("NotAuth", "You are not allowed here! Login to continue");
            return "redirect:/login";

        } else {
            CustomUserDetails loggedUser = ((CustomUserDetails)principal);
            session.setAttribute("loggedInUserEmail", loggedUser.getUsername());
            session.setAttribute("loggedInUserId", loggedUser.getUserId());
            session.setAttribute("loggedInUserFirstName", loggedUser.getFirstName());
            session.setAttribute("loggedInUserLastName", loggedUser.getLastName());
            session.setMaxInactiveInterval(60);
            redirectAttributes.addFlashAttribute("loginSuccess", "You have successfully logged In!");
            log.info("Session set");
            return "redirect:/home/dashboard";
        }

    }

    public static void updateSession(HttpSession session, RedirectAttributes redirectAttributes, long id, UserUpdateForm updateForm) {

            session.setAttribute("loggedInUserEmail", updateForm.getEmail());
            session.setAttribute("loggedInUserId", id);
            session.setAttribute("loggedInUserFirstName", RegisterUtil.formatName(updateForm.getFirstName()));
            session.setAttribute("loggedInUserLastName", RegisterUtil.formatName(updateForm.getLastName()));
            redirectAttributes.addFlashAttribute("successEdit","Updated Successfully");
            log.info("Session updated");

    }

    public static String checkSession(HttpSession session, RedirectAttributes redirectAttributes, Integer id, String route) {

//                checks if current user is logged in
        if (!(session.getAttribute("loggedInUserId") instanceof Long)) {
            redirectAttributes.addFlashAttribute("NotAuth", "This page either does not exist or you are not authorized");
            return "redirect:/error/404";
        }
//         checks if current user matches path variable
        if (!id.toString().equals(session.getAttribute("loggedInUserId").toString())) {
            redirectAttributes.addFlashAttribute("NotAuth", "This page either does not exist or you are not authorized");
            return "redirect:/error/404";
        }

        return route;

    }

}
