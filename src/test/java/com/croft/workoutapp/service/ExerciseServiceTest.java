package com.croft.workoutapp.service;

import com.croft.workoutapp.model.Exercise;
import com.croft.workoutapp.model.ExerciseForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpSession;

@ActiveProfiles("test")
@SpringBootTest
public class ExerciseServiceTest {

    @Autowired
    ExerciseService exerciseService;

    @Test
    void testCreateExercise(){
        Exercise expected = new Exercise();
        expected.setU_id(1);
        expected.setE_name("Jump Rope");
        expected.setE_type("Cardio");
        expected.setE_reps((long) 10);
        expected.setE_sets((long) 5);
        exerciseService.createExercise(expected);
    }

}
