package com.intecbrussel.bankinguserservice.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "user_tb")
@Entity
public class AuthUser {


    @Id
    @Column(name = "email_address", unique = true, nullable = false)
    private String emailAddress;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "admin")
    private boolean admin;

    public AuthUser(String email, String password, String firstName, String lastName, Boolean admin) {
        this.emailAddress = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
    }
}
