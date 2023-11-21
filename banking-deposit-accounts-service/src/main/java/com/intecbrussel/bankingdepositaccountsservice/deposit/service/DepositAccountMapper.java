package com.intecbrussel.bankingdepositaccountsservice.deposit.service;

import com.intecbrussel.bankingdepositaccountsservice.deposit.model.AccountDeposit;
import com.intecbrussel.commonsservice.dto.AccountDepositDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepositAccountMapper {

    AccountDepositDTO accountDepositToDTO(AccountDeposit accountDeposit);
    AccountDeposit toAccountDeposit (AccountDepositDTO accountDepositDTO);

}
