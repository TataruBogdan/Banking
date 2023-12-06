package com.intecbrussel.bankinguserservice.user.repository;

import com.intecbrussel.bankinguserservice.user.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
}
