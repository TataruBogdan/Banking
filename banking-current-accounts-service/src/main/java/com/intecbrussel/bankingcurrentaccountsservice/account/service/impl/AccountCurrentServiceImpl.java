package com.intecbrussel.bankingcurrentaccountsservice.account.service.impl;

import com.intecbrussel.bankingcurrentaccountsservice.account.dao.AccountRepository;
import com.intecbrussel.bankingcurrentaccountsservice.account.model.AccountCurrent;
import com.intecbrussel.bankingcurrentaccountsservice.account.service.AccountCurrentMapper;
import com.intecbrussel.bankingcurrentaccountsservice.account.service.AccountCurrentService;
import com.intecbrussel.commonsservice.dto.AccountCurrentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static com.intecbrussel.commonsservice.dto.idGen.idGenerator.idGen;
import static com.intecbrussel.commonsservice.dto.types.CurrentStatus.ACTIVE;

@RequiredArgsConstructor
@Service
public class AccountCurrentServiceImpl implements AccountCurrentService {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final AccountCurrentMapper accountCurrentMapper;
    @Override
    public List<AccountCurrentDTO> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(accountCurrent -> accountCurrentMapper.accountToDTO(accountCurrent))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountCurrentDTO> getByIban(String iban) {
        return accountRepository.findById(iban)
                .map(accountCurrent -> accountCurrentMapper.accountToDTO(accountCurrent));
    }

    @Override
    public List<AccountCurrentDTO> getByIndividualId(int individualId) {
        List<AccountCurrent> accountRepositoryByIndividualId = accountRepository.findByIndividualId(individualId);
        List<AccountCurrentDTO> accountCurrentDTO = accountCurrentMapper.toAccountCurrentDTO(accountRepositoryByIndividualId);

        return accountCurrentDTO;

    }

    @Override
    public AccountCurrentDTO updateBalanceAccount(String iban, Double balance) {
        AccountCurrent accountCurrent= accountRepository.getReferenceById(iban);
        accountCurrent.setBalance(balance);
        AccountCurrent savedAccountCurrent = accountRepository.save(accountCurrent);

        AccountCurrentDTO accountCurrentDTO = accountCurrentMapper.accountToDTO(savedAccountCurrent);
        return accountCurrentDTO;

    }

    @Override
    public AccountCurrentDTO creditBalanceAccount(String iban, Double balance) {
        AccountCurrent accountCurrent = accountRepository.getReferenceById(iban);
        Double currentBalance = accountCurrent.getBalance();
        accountCurrent.setBalance(currentBalance + balance);
        AccountCurrent saveAccountCurrent = accountRepository.save(accountCurrent);

        return accountCurrentMapper.accountToDTO(saveAccountCurrent);
    }

    @Override
    public AccountCurrentDTO debitBalanceAccount(String iban, Double balance) {
        AccountCurrent accountCurrent = accountRepository.getReferenceById(iban);
        Double currentBalance = accountCurrent.getBalance();
        accountCurrent.setBalance(currentBalance - balance);
        AccountCurrent saveAccountCurrent = accountRepository.save(accountCurrent);

        return accountCurrentMapper.accountToDTO(saveAccountCurrent);
    }

    @Override
    public AccountCurrentDTO createIndividualAccount(int individualId) {
        AccountCurrent accountCurrent = new AccountCurrent();
        accountCurrent.setIban(idGen("CURR"));
        accountCurrent.setBalance(0.0);
        accountCurrent.setIndividualId(individualId);
        accountCurrent.setStartDate(LocalDateTime.now());
        accountCurrent.setCurrentStatus(ACTIVE);
        accountCurrent.setPrimaryAccount(true);
        AccountCurrent savedAccountCurrent = accountRepository.save(accountCurrent);
        AccountCurrentDTO savedAccountCurrentDTO = accountCurrentMapper.accountToDTO(savedAccountCurrent);

        return savedAccountCurrentDTO;
    }

    @Override
    public void deleteAccountByIban(String iban) {
        accountRepository.deleteById(iban);
    }
}
