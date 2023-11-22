package com.intecbrussel.bankingcurrentaccountsservice.account.service;


import com.intecbrussel.bankingcurrentaccountsservice.account.model.AccountCurrent;

import com.intecbrussel.commonsservice.dto.AccountCurrentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountCurrentMapper {

    @InheritInverseConfiguration
    AccountCurrentDTO accountToDTO(AccountCurrent accountCurrent);
    List<AccountCurrentDTO> toAccountCurrentDTO(List<AccountCurrent> accountCurrentList);

    @Mapping(target = "individualId", source = "individualId")
    AccountCurrent toAccountCurrent(AccountCurrentDTO accountCurrentDTO);
}
