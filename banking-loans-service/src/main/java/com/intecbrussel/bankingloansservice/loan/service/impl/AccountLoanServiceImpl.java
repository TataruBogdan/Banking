package com.intecbrussel.bankingloansservice.loan.service.impl;

import com.intecbrussel.bankingloansservice.loan.dao.LoanRepository;
import com.intecbrussel.bankingloansservice.loan.model.AccountLoan;
import com.intecbrussel.bankingloansservice.loan.service.AccountLoanMapper;
import com.intecbrussel.bankingloansservice.loan.service.AccountLoanService;
import com.intecbrussel.commonsservice.dto.AccountLoanDTO;
import com.intecbrussel.commonsservice.dto.types.LoanStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.intecbrussel.commonsservice.dto.idGen.idGenerator.idGen;

@RequiredArgsConstructor
@Service
public class AccountLoanServiceImpl implements AccountLoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountLoanMapper accountLoanMapper;

    @Override
    public List<AccountLoanDTO> getAll() {
        List<AccountLoan> loanRepositoryAll = loanRepository.findAll();

         return loanRepositoryAll.stream()
                .map(accountLoan -> accountLoanMapper.accountLoanToDTO(accountLoan))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountLoanDTO> getByIban(String iban) {
        Optional<AccountLoan> loanRepositoryById = loanRepository.findById(iban);

        return loanRepositoryById.map(accountLoan -> accountLoanMapper.accountLoanToDTO(accountLoan));
    }

    @Override
    public List<AccountLoanDTO> getAllByIndividualId(int individualId) {
        List<AccountLoan> listLoanRepositoryByIndividualId = loanRepository.findByIndividualId(individualId);

        return listLoanRepositoryByIndividualId.stream()
                .map(accountLoan -> accountLoanMapper.accountLoanToDTO(accountLoan))
                .collect(Collectors.toList());
    }

    @Override
    public AccountLoanDTO createIndividualAccountLoan(int individualId, int period, double amount) {
        AccountLoan accountLoan = new AccountLoan();
        accountLoan.setIban(idGen("LOAN"));
        accountLoan.setIndividualId(individualId);
        accountLoan.setLoanAmount(amount);
        accountLoan.setPeriod(period);
        accountLoan.setInterestRate(interestRate);
        accountLoan.setStartDate(LocalDateTime.now());
        accountLoan.setLoanStatus(LoanStatus.ACTIVE);
        accountLoan.setPrincipal(0.00);

        AccountLoan savedAccountLoan = loanRepository.save(accountLoan);
        return accountLoanMapper.accountLoanToDTO(savedAccountLoan);
    }

    @Override
    public void deleteAccountLoanById(String iban) {
        loanRepository.deleteById(iban);
    }
}
