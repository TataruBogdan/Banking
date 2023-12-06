package com.intecbrussel.bankinguserservice.user.service.impl;

import com.intecbrussel.bankinguserservice.user.model.LoginRequest;
import com.intecbrussel.bankinguserservice.user.model.LoginResponse;
import com.intecbrussel.bankinguserservice.user.repository.AuthUserRepository;
import com.intecbrussel.bankinguserservice.user.service.AuthService;
import com.intecbrussel.bankinguserservice.user.service.AuthUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserImpl implements AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthUserMapper authUserMapper;


    @Override
    public boolean createUser(String firsName, String lastName, String email, String password) {
        return false;
    }

    @Override
    public Optional<LoginResponse> login(LoginRequest loginRequest) {
        return Optional.empty();
    }
}
