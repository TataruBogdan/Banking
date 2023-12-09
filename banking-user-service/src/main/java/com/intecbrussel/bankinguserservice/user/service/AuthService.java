package com.intecbrussel.bankinguserservice.user.service;

import com.intecbrussel.bankinguserservice.security.JwtUtil;
import com.intecbrussel.bankinguserservice.user.model.AuthUser;
import com.intecbrussel.bankinguserservice.user.model.LoginRequest;
import com.intecbrussel.bankinguserservice.user.model.LoginResponse;
import com.intecbrussel.bankinguserservice.user.repository.AuthUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private AuthUserRepository authUserRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(AuthUserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authUserRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean createUser(String firstName, String lastName, String email, String password, Boolean admin) {
        AuthUser user = new AuthUser(email, bCryptPasswordEncoder.encode(password), firstName, lastName, admin);

        boolean exists = authUserRepository.existsById(email);

        if (!exists) {
            authUserRepository.save(user);
            return true;
        }

        return false;
    }

    public Optional<LoginResponse> login(LoginRequest loginRequest) {
        Optional<AuthUser> optionalUser = findUser(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        AuthUser user = optionalUser.get();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        String email = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = "USER";

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                role = "ADMIN";
                break;
            }
        }

        String token = jwtUtil.createToken(user, role);
        System.out.println("THIS IS THE BUILD-TOKEN " + token);
        LoginResponse loginResponse = new LoginResponse(email, token, role);

        return Optional.of(loginResponse);
    }

    public Optional<AuthUser> findUser(String email) {
        return authUserRepository.findById(email);
    }

    public void updateUser(AuthUser user) {
        authUserRepository.saveAndFlush(user);
    }

    public Optional<List<AuthUser>> findAllUsers() {

        List<AuthUser> dbAllUsers = authUserRepository.findAll();

        if (dbAllUsers.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(dbAllUsers);
    }
}
