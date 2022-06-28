package com.croft.workoutapp.model;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "name", nullable = false, length = 45)
    private String e_name;

    @Column(name = "type", nullable = false, length = 45)
    private String e_type;

    @Column(name = "sets", nullable = false, length = 64)
    private Long e_sets;

    @Column(name = "reps", nullable = false, length = 45)
    private Long e_reps;

    @Column(name = "user_Id", length = 45)
    private long u_id;

}
