package com.croft.workoutApp.service;

import com.croft.workoutApp.model.Exercise;
import com.croft.workoutApp.model.ExerciseForm;
import com.croft.workoutApp.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    public List<Exercise> getAllExercises();

    public Optional<Exercise> getExerciseById(long id);

    public void createExerciseFromForm(ExerciseForm exerciseForm, HttpSession session);

    public void updateExercise(long id,  ExerciseForm exerciseForm);

    public void deleteExercise(long id);

    public List<Exercise> getAllExercisesByUserId(long id);

}
