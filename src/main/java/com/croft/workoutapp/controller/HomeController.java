package com.croft.workoutapp.controller;

import com.croft.workoutapp.model.Exercise;
import com.croft.workoutapp.model.ExerciseForm;
import com.croft.workoutapp.service.ExerciseService;
import com.croft.workoutapp.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value="/home")
public class HomeController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("")
    public String viewHomePage(HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("loggedInUserId") != null) {
            redirectAttributes.addFlashAttribute("alreadyLogged", "You are already logged in!");
            return "redirect:/home/dashboard";
        }
        return SessionUtil.setSession(session, redirectAttributes);

    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            log.error("You are not logged in!");
            redirectAttributes.addFlashAttribute("NotAuth", "You are not allowed here! Login to continue");
            return "redirect:/login";
        }

        List<Exercise> UserExercises = exerciseService.getAllExercisesByUserId((long) session.getAttribute("loggedInUserId"));
        model.addAttribute("listExercises", UserExercises);

        return "dashboard";
    }

    @GetMapping("/dashboard/edit/{id}")
    public String editExercise(@PathVariable("id") Integer id, @ModelAttribute("exerciseForm") ExerciseForm exerciseForm, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        Exercise exercise = exerciseService.getExerciseById(id).get();
        model.addAttribute("exercise", exercise);

        return SessionUtil.checkSession(session, redirectAttributes, "exercise_edit");
    }

    @PostMapping("/dashboard/edit/{id}/save")
    public String saveExercise(@PathVariable("id") Integer id, @Valid @ModelAttribute("exerciseForm") ExerciseForm exerciseForm, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        Exercise exercise = exerciseService.getExerciseById(id).orElseThrow();
        model.addAttribute("exercise", exercise);

        //        if validation errors
        if (result.hasErrors()) {
            return "exercise_edit";
        }

        exerciseService.updateExercise(id, exerciseForm);
        redirectAttributes.addFlashAttribute("updateExercise", "You successfully updated the exercise");
        return "redirect:/home/dashboard";
    }

    @GetMapping("/dashboard/showDelete/{id}")
    public String showDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        Exercise exercise = exerciseService.getExerciseById(id).orElseThrow();
        model.addAttribute("exercise", exercise);

        return SessionUtil.checkSession(session, redirectAttributes, "exercise_delete");
    }

    @GetMapping(value = "/dashboard/confirmDelete/{id}")
    public String deleteExercise(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, HttpSession session, Authentication authentication) {
        log.info("Account Delete Route Ran");

//        create user from form
        exerciseService.deleteExercise(id);
        redirectAttributes.addFlashAttribute("exerciseDelete","Exercise has been deleted");
        return "redirect:/home/dashboard";
    }
}
