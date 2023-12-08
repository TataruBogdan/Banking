package com.intecbrussel.bankinguserservice.user.controller;

import com.intecbrussel.bankinguserservice.user.model.LoginRequest;
import com.intecbrussel.bankinguserservice.user.model.LoginResponse;
import com.intecbrussel.bankinguserservice.user.model.RegisterRequest;
import com.intecbrussel.bankinguserservice.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<LoginResponse> optionalResponse = authService.login(loginRequest);

            if (optionalResponse.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(optionalResponse.get());

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        boolean success = authService.createUser(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                registerRequest.getPassword()
        );

        if (success) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }


}
