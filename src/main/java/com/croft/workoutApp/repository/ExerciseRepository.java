package com.croft.workoutApp.repository;

import com.croft.workoutApp.model.Exercise;
import com.croft.workoutApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e FROM Exercise e WHERE e.u_id = ?1")
    public List<Exercise> findAllByUserId(long id);
}
