package com.croft.workoutApp.repository;

import com.croft.workoutApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findAllByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Optional<User> findByEmail(String email);
    public Optional<User> findByPassword(String password);
    public Optional<User> findByEmailAndPassword(String email, String password);



}
