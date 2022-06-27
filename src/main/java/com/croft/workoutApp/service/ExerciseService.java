package com.croft.workoutApp.service;

import com.croft.workoutApp.model.Exercise;
import com.croft.workoutApp.model.ExerciseForm;
import com.croft.workoutApp.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    List<Exercise> getAllExercises();

    Optional<Exercise> getExerciseById(long id);

    void createExerciseFromForm(ExerciseForm exerciseForm, HttpSession session);

    void updateExercise(long id,  ExerciseForm exerciseForm);

    void deleteExercise(long id);

    List<Exercise> getAllExercisesByUserId(long id);

}
