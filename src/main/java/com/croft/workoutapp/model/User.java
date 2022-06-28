package com.croft.workoutapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @Email
    @NotEmpty
    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Size(min=8)
    @NotEmpty
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @NotEmpty
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @NotEmpty
    @Column(name = "last_name", length = 45)
    private String lastName;

}
