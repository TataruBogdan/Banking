package com.intecbrussel.bankingloansservice.loan.controller;

import com.intecbrussel.bankingloansservice.loan.controller.client.IndividualRestClient;
import com.intecbrussel.bankingloansservice.loan.service.AccountLoanService;
import com.intecbrussel.commonsservice.dto.AccountLoanDTO;
import com.intecbrussel.commonsservice.dto.ArgsDTO;
import com.intecbrussel.commonsservice.dto.IndividualDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController

public class AccountLoanController {

    @Autowired
    private AccountLoanService accountLoanService;

    @Autowired
    private IndividualRestClient individualRestClient;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/accounts-loan")
    public ResponseEntity<List<AccountLoanDTO>> retrieveAllAccountsLoan(){
        List<AccountLoanDTO> accountLoanDTOList = accountLoanService.getAll();
        if (accountLoanDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            List<AccountLoanDTO> newAccountLoanDTOList = new ArrayList<>();
            for (AccountLoanDTO accountLoan : accountLoanDTOList) {
                IndividualDTO individualDTO = individualRestClient.getIndividualById(accountLoan.getIndividualId());
                accountLoan.setIndividualDTO(individualDTO);
                newAccountLoanDTOList.add(accountLoan);
            }
            return ResponseEntity.ok(newAccountLoanDTOList);
        }
    }

    @GetMapping(value = "/accounts-loan/{individualId}")
    public ResponseEntity<List<AccountLoanDTO>> retrieveLoanIndividual(@PathVariable("individualId") int individualId) {
        List<AccountLoanDTO> accountLoanAllByIndividualId = accountLoanService.getAllByIndividualId(individualId);

        if (accountLoanAllByIndividualId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        IndividualDTO individualDTO = individualRestClient.getIndividualById(individualId);
        for (AccountLoanDTO accountLoan : accountLoanAllByIndividualId) {
            accountLoan.setIndividualDTO(individualDTO);
        }
        return ResponseEntity.ok(accountLoanAllByIndividualId);

    }

    @GetMapping("/account-loan/{iban}")
    public ResponseEntity<AccountLoanDTO> retrieveAccountLoan(@PathVariable("iban") String iban) {

        Optional<AccountLoanDTO> accountLoanByIban = accountLoanService.getByIban(iban);

        if(accountLoanByIban.isPresent()) {

            IndividualDTO individualDTO = individualRestClient.getIndividualById(accountLoanByIban.get().getIndividualId());
            accountLoanByIban.get().setIndividualDTO(individualDTO);
            return ResponseEntity.ok(accountLoanByIban.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(value = "/create-account-loan/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountLoanDTO> createAccountLoanForIndividual(@PathVariable("id") int id, @RequestBody ArgsDTO argsDTO){
        AccountLoanDTO individualAccountLoan = accountLoanService.createIndividualAccountLoan(id, argsDTO.getMonths(), argsDTO.getAmount());

        IndividualDTO individualDTO = individualRestClient.getIndividualById(id);
        individualAccountLoan.setIndividualDTO(individualDTO);
        return ResponseEntity.ok(individualAccountLoan);
    }

    @DeleteMapping(value = "delete/account-loan/{iban}")
    public void deleteLoanFromRepository(@PathVariable("iban") String iban) {
        accountLoanService.deleteAccountLoanById(iban);
    }

}
