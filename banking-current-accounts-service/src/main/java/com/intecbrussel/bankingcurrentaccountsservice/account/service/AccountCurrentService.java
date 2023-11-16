package com.intecbrussel.bankingcurrentaccountsservice.account.service;

import com.intecbrussel.commonsservice.dto.AccountCurrentDTO;

import java.util.List;
import java.util.Optional;

public interface AccountCurrentService {

    List<AccountCurrentDTO> getAll();
    Optional<AccountCurrentDTO> getByIban(String iban);
    List<AccountCurrentDTO> getByIndividualId(int individualId);
    AccountCurrentDTO updateBalanceAccount(String iban, Double balance);
    AccountCurrentDTO creditBalanceAccount(String iban, Double balance);
    AccountCurrentDTO debitBalanceAccount(String iban, Double balance);

    AccountCurrentDTO createIndividualAccount(int individualId);
    void deleteAccountByIban(String iban);
}
