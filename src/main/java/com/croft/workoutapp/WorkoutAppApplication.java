package com.croft.workoutapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WorkoutAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutAppApplication.class, args);
		log.info("Main app started up");
	}

}
