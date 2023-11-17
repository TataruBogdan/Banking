package com.intecbrussel.bankingcurrentaccountsservice.account.service;

import com.intecbrussel.bankingcurrentaccountsservice.account.model.AccountCurrent;

import com.intecbrussel.commonsservice.dto.AccountCurrentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountCurrentMapper {

    AccountCurrentDTO accountToDTO(AccountCurrent accountCurrent);
    List<AccountCurrentDTO> toAccountCurrentDTO(List<AccountCurrent> accountCurrentList);

    AccountCurrent toAccountCurrent(AccountCurrentDTO accountCurrentDTO);
}
