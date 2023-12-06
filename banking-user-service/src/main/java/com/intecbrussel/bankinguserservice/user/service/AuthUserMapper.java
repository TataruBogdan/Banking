package com.intecbrussel.bankinguserservice.user.service;

import com.intecbrussel.bankinguserservice.user.model.AuthUser;
import com.intecbrussel.commonsservice.dto.AuthUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    AuthUserDTO toDTO(AuthUser authUser);
    AuthUser toAuthUser(AuthUserDTO authUserDTO);
}
