package com.croft.workoutApp.controller;

import com.croft.workoutApp.model.Exercise;
import com.croft.workoutApp.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value="/exercises")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("")
    public String viewExercisePage(Model model) {
        List<Exercise> listExercises = exerciseService.getAllExercises();
        model.addAttribute("listExercises", listExercises);
        return "exercises";
    }

    @GetMapping("/add")
    public String addExercisePage() {
        return "exerciseAdd";
    }

}
