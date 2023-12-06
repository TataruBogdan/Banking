package com.intecbrussel.bankingdepositaccountsservice.deposit.service.impl;

import com.intecbrussel.bankingdepositaccountsservice.deposit.dao.DepositRepository;
import com.intecbrussel.bankingdepositaccountsservice.deposit.model.AccountDeposit;
import com.intecbrussel.bankingdepositaccountsservice.deposit.service.DepositAccountMapper;

import com.intecbrussel.bankingdepositaccountsservice.deposit.service.DepositAccountService;
import com.intecbrussel.commonsservice.dto.AccountDepositDTO;
import com.intecbrussel.commonsservice.dto.types.DepositStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.intecbrussel.commonsservice.dto.idGen.idGenerator.idGen;

@RequiredArgsConstructor
@Service
public class AccountDepositServiceImpl implements DepositAccountService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private DepositAccountMapper depositAccountMapper;

    @Override
    public List<AccountDepositDTO> getAll() {
        List<AccountDeposit> depositRepositoryAll = depositRepository.findAll();

        return depositRepositoryAll.stream()
                .map(accountDeposit -> depositAccountMapper.accountDepositToDTO(accountDeposit))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDepositDTO> getByIban(String iban) {
        Optional<AccountDeposit> depositRepositoryById = depositRepository.findById(iban);

        return depositRepositoryById.map(accountDeposit -> depositAccountMapper.accountDepositToDTO(accountDeposit));
    }

    @Override
    public List<AccountDepositDTO> getAllByIndividualId(int individualId) {
        List<AccountDeposit> listDepositsByIndividualId = depositRepository.findByIndividualId(individualId);

        return listDepositsByIndividualId.stream()
                .map(accountDeposit -> depositAccountMapper.accountDepositToDTO(accountDeposit))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDepositDTO updateBalanceDeposit(String iban, Double balance) {
        AccountDeposit depositAccount = depositRepository.getReferenceById(iban);
        depositAccount.setBalance(balance);
        AccountDeposit savedDeposit = depositRepository.save(depositAccount);

        return depositAccountMapper.accountDepositToDTO(savedDeposit);
    }

//    @Override
//    public AccountDepositDTO creditBalanceDeposit(String iban, Double balance) {
//        return null;
//    }

    @Override
    public AccountDepositDTO debitBalanceDeposit(String iban, Double balance) {
        AccountDeposit depositAccount = depositRepository.getReferenceById(iban);
        double currentBalance = depositAccount.getBalance();
        depositAccount.setBalance(currentBalance - balance);
        AccountDeposit savedAccountDeposit = depositRepository.save(depositAccount);

        return depositAccountMapper.accountDepositToDTO(savedAccountDeposit);
    }

    @Override
    public AccountDepositDTO createIndividualAccountDeposit(int individualId, int months, double amount) {
        AccountDeposit accountDeposit = new AccountDeposit();
        accountDeposit.setIban(idGen("DEP"));
        accountDeposit.setDepositAmount(amount);
        accountDeposit.setBalance(amount);
        accountDeposit.setIndividualId(individualId);
        //maturity date - TODO?
        accountDeposit.setMaturityMonths(months);
        accountDeposit.setInterestRate(interestRate);
        accountDeposit.setSelfCapitalization(true);
        accountDeposit.setMaturityIban(accountDeposit.getIban());
        accountDeposit.setStartDate(LocalDateTime.now());
        accountDeposit.setStatus(DepositStatus.ACTIVE);

        AccountDeposit savedAccountDepositIndividual = depositRepository.save(accountDeposit);
        return depositAccountMapper.accountDepositToDTO(savedAccountDepositIndividual);
    }

    @Override
    public void deleteAccountDepositById(String iban) {
        depositRepository.deleteById(iban);
    }
}
