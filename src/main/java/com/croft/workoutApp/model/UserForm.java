package com.croft.workoutApp.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @Email(message="Email must be a valid email")
    @NotEmpty(message="Email cannot be empty")
    private String email;

    @Size(min=8, message="Password must be larger than 8 characters")
    @NotEmpty(message = "Password can't be empty")
    private String password;

    @Size(min=8, message="Password must be larger than 8 characters")
    @NotEmpty(message = "Password can't be empty")
    private String confirm;


    @NotEmpty(message = "First name can't be empty")
    private String firstName;

    @NotEmpty(message = "Last name can't be empty")
    private String lastName;

}
