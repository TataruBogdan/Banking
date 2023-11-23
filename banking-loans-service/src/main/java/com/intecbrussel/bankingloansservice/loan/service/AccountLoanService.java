package com.intecbrussel.bankingloansservice.loan.service;

import com.intecbrussel.commonsservice.dto.AccountLoanDTO;

import java.util.List;
import java.util.Optional;

public interface AccountLoanService {
    double interestRate = 5.00;

    List<AccountLoanDTO> getAll();

    Optional<AccountLoanDTO> getByIban(String iban);
    List<AccountLoanDTO> getAllByIndividualId(int individualId);
    AccountLoanDTO createIndividualAccountLoan(int individualId, int period, double amount);
    void deleteAccountLoanById(String iban);
}
