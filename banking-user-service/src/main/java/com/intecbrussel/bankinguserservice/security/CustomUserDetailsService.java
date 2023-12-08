package com.intecbrussel.bankinguserservice.security;

import com.intecbrussel.bankinguserservice.user.model.AuthUser;
import com.intecbrussel.bankinguserservice.user.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AuthUser> authUserRepositoryByEmail = authUserRepository.findById(email);

        if (authUserRepositoryByEmail.isEmpty()) {
            throw new UsernameNotFoundException(email + " not found");
        }

        AuthUser authUser = authUserRepositoryByEmail.get();

        return User.builder()
                .username(authUser.getEmailAddress())
                .password(authUser.getPassword())
                .roles(authUser.isAdmin() ? "ADMIN" : "USER")
                .build();
    }
}
