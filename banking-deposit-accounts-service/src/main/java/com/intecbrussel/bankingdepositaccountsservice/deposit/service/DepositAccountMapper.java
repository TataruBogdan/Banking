package com.intecbrussel.bankingdepositaccountsservice.deposit.service;



import com.intecbrussel.bankingdepositaccountsservice.deposit.model.AccountDeposit;
import com.intecbrussel.commonsservice.dto.AccountDepositDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepositAccountMapper {

    @InheritInverseConfiguration
    AccountDepositDTO accountDepositToDTO(AccountDeposit accountDeposit);
    @Mapping(target = "individualId", source = "individualId")
    AccountDeposit toAccountDeposit (AccountDepositDTO accountDepositDTO);

}
