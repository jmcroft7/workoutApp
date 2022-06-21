package com.croft.workoutApp.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private String email;

    @Size(min=8, message="Not big enough")
    private String password;

    @NotEmpty(message = "can't be empty!")
    private String firstName;

    @NotNull(message = "can't be empty!")
    private String lastName;

}
