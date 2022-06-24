package com.croft.workoutApp.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseForm {


    @NotEmpty(message="Exercise name cannot be empty")
    private String e_name;

    @NotEmpty(message="type cannot be empty")
    private String e_type;

    @NotEmpty(message="sets cannot be empty")
    private Long e_sets;

    @NotEmpty(message="Reps cannot be empty")
    private Long e_reps;

}
