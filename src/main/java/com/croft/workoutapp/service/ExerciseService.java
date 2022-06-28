package com.croft.workoutapp.service;

import com.croft.workoutapp.model.Exercise;
import com.croft.workoutapp.model.ExerciseForm;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    List<Exercise> getAllExercises();

    Optional<Exercise> getExerciseById(long id);

    void createExercise(Exercise exercise);

    void createExerciseFromForm(ExerciseForm exerciseForm, HttpSession session);

    void updateExercise(long id,  ExerciseForm exerciseForm);

    void deleteExercise(long id);

    List<Exercise> getAllExercisesByUserId(long id);

}
