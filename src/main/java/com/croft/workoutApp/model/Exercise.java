package com.croft.workoutApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long e_id;

    @NotEmpty
    @Column(name = "name", nullable = false, length = 45)
    private String e_name;

    @NotEmpty
    @Column(name = "type", nullable = false, length = 45)
    private String e_type;

    @NotEmpty
    @Column(name = "sets", nullable = false, length = 64)
    private Long e_sets;

    @NotEmpty
    @Column(name = "reps", nullable = false, length = 45)
    private Long e_reps;

    @NotEmpty
    @Column(name = "user_Id", length = 45)
    private long u_id;

}
