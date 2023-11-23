package com.intecbrussel.bankingloansservice.loan.service;

import com.intecbrussel.bankingloansservice.loan.model.AccountLoan;
import com.intecbrussel.commonsservice.dto.AccountLoanDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountLoanMapper {


    AccountLoan toAccountLoan(AccountLoanDTO accountLoanDTO);

    AccountLoanDTO accountLoanToDTO(AccountLoan accountLoan);

}
