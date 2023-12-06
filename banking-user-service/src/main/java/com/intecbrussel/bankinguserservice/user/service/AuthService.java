package com.intecbrussel.bankinguserservice.user.service;

import com.intecbrussel.bankinguserservice.user.model.AuthUser;
import com.intecbrussel.bankinguserservice.user.model.LoginRequest;
import com.intecbrussel.bankinguserservice.user.model.LoginResponse;

import java.util.Optional;

public interface AuthService {


    boolean createUser(
            String firsName,
            String lastName,
            String email,
            String password);

    Optional<LoginResponse> login(LoginRequest loginRequest);

}
