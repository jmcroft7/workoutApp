package com.croft.workoutapp.serviceimpl;

import com.croft.workoutapp.model.Exercise;
import com.croft.workoutapp.model.ExerciseForm;
import com.croft.workoutapp.repository.ExerciseRepository;
import com.croft.workoutapp.service.ExerciseService;
import com.croft.workoutapp.utils.RegisterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceIMPL implements ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepo;

    @Override
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();
        exerciseRepo.findAll().forEach(exercises::add);
        return exercises;
    }

    @Override
    public Optional<Exercise> getExerciseById(long id) {
        return exerciseRepo.findById(id);
    }


    @Override
    public void createExercise(Exercise exercise) {
        exerciseRepo.save(exercise);
    }

    @Override
    public void createExerciseFromForm(ExerciseForm exerciseForm, HttpSession session) {

        String name = RegisterUtil.formatName(exerciseForm.getE_name());
        String type = RegisterUtil.formatName(exerciseForm.getE_type());

        Exercise _exercise = new Exercise();
        _exercise.setE_name(name);
        _exercise.setE_type(type);
        _exercise.setE_reps(exerciseForm.getE_reps());
        _exercise.setE_sets(exerciseForm.getE_sets());
        if (session.getAttribute("loggedInUserId") == null) {
            _exercise.setU_id(1);
        }
        else {
            _exercise.setU_id((long) session.getAttribute("loggedInUserId"));
        }
        exerciseRepo.save(_exercise);

    }

    @Override
    public void updateExercise(long id, ExerciseForm exerciseForm) {
        Exercise _exercise = exerciseRepo.findById(id).orElseThrow();

        String name = RegisterUtil.formatName(exerciseForm.getE_name());
        String type = RegisterUtil.formatName(exerciseForm.getE_type());

        _exercise.setE_name(name);
        _exercise.setE_type(type);
        _exercise.setE_reps(exerciseForm.getE_reps());
        _exercise.setE_sets(exerciseForm.getE_sets());

        exerciseRepo.save(_exercise);


    }

    @Override
    public void deleteExercise(long id) {
        Optional<Exercise> exerciseData = exerciseRepo.findById(id);

        if (exerciseData.isPresent()) {
            exerciseRepo.deleteById(id);
        }
    }

    @Override
    public List<Exercise> getAllExercisesByUserId(long id) {
        return exerciseRepo.findAllByUserId(id);
    }
}
