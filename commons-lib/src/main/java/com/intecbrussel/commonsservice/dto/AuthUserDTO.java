package com.intecbrussel.commonsservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AuthUserDTO {

    @NotNull
    @Email(regexp = "^(.+)@(\\S+)$", message = " Please provide a valid email")
    private String emailAddress;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private boolean admin;

}
