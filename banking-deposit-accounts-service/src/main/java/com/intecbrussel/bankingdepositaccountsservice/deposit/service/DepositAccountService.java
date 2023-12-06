package com.intecbrussel.bankingdepositaccountsservice.deposit.service;

import com.intecbrussel.commonsservice.dto.AccountDepositDTO;

import java.util.List;
import java.util.Optional;

public interface DepositAccountService {

    // can I put in the interface interest rate ?
    double interestRate = 0.05;
    List<AccountDepositDTO> getAll();
    Optional<AccountDepositDTO> getByIban(String iban);
    List<AccountDepositDTO> getAllByIndividualId(int individualId);
    AccountDepositDTO updateBalanceDeposit(String iban, Double balance);
    // AccountDepositDTO creditBalanceDeposit(String iban, Double balance);
    AccountDepositDTO debitBalanceDeposit(String iban, Double balance);
    AccountDepositDTO createIndividualAccountDeposit(int individualId, int months, double amount);
    void deleteAccountDepositById(String iban);

}
