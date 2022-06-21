package com.croft.workoutApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    @Size(min=8)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    @NotEmpty(message = "can't be empty!")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

}
