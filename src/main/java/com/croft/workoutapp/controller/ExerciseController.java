package com.croft.workoutapp.controller;

import com.croft.workoutapp.model.Exercise;
import com.croft.workoutapp.model.ExerciseForm;
import com.croft.workoutapp.service.ExerciseService;
import com.croft.workoutapp.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="/exercises")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("")
    public String viewExercisePage(Model model) {
        log.info("Exercise List Route Ran");
        List<Exercise> listExercises = exerciseService.getAllExercises();
        model.addAttribute("listExercises", listExercises);
        return "exercises";
    }

    @GetMapping("/{id}/add")
    public String addExercisePage(@PathVariable("id") Integer id, @Valid @ModelAttribute("exerciseForm") ExerciseForm exerciseForm, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("Exercise Add Route Ran");

//                checks if user is authenticated and that the user id matches the route id
        return SessionUtil.checkSession(session, redirectAttributes, id, "exerciseAdd");
    }

    @PostMapping(value = "/{id}/save")
    public String saveExercisePage(@PathVariable("id") Integer id, @Valid @ModelAttribute("exerciseForm") ExerciseForm exerciseForm, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("Exercise Save Route Ran");

//         checks if current user matches path variable
        if (!id.toString().equals(session.getAttribute("loggedInUserId").toString())) {
            redirectAttributes.addFlashAttribute("NotAuth", "This page either does not exist or you are not authorized");
            return "redirect:/error/404";
        }

//        if validation errors
        if (result.hasErrors()) {
            return "/exerciseAdd";
        }

        exerciseService.createExerciseFromForm(exerciseForm, session);
        redirectAttributes.addFlashAttribute("exerciseSuccess", "You successfully created an exercise");
        return "redirect:/exercises";
    }

}
