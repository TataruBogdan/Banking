package com.intecbrussel.bankingdepositaccountsservice.deposit.controller;

import com.intecbrussel.bankingdepositaccountsservice.deposit.controller.client.IndividualRestClient;
import com.intecbrussel.bankingdepositaccountsservice.deposit.service.DepositAccountService;
import com.intecbrussel.commonsservice.dto.AccountDepositDTO;

import com.intecbrussel.commonsservice.dto.ArgsDTO;
import com.intecbrussel.commonsservice.dto.CreditAccountDepositDTO;
import com.intecbrussel.commonsservice.dto.IndividualDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountDepositController {

    @Autowired
    private final DepositAccountService depositAccountService;

    @Autowired
    private IndividualRestClient individualRestClient;

    @GetMapping("/accounts-deposit")
    public ResponseEntity<List<AccountDepositDTO>> retrieveAllAccountsDeposit() {

        List<AccountDepositDTO> accountDepositDTOList = depositAccountService.getAll();
        if (accountDepositDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            List<AccountDepositDTO> newAccountDepositDTOList = new ArrayList<>();
            for (AccountDepositDTO accountDeposit: accountDepositDTOList) {
                IndividualDTO individualDTO = individualRestClient.getIndividualById(accountDeposit.getIndividualId());
                accountDeposit.setIndividualDTO(individualDTO);
                newAccountDepositDTOList.add(accountDeposit);
            }
            return ResponseEntity.ok(newAccountDepositDTOList);
        }
    }

    @GetMapping("/accounts-deposit/{iban}")
    public ResponseEntity<AccountDepositDTO> retrieveAccountDeposit(@PathVariable String iban) {
        Optional<AccountDepositDTO> depositAccountByIban = depositAccountService.getByIban(iban);

        if (depositAccountByIban.isPresent()) {
            IndividualDTO individualDTO = individualRestClient.getIndividualById(depositAccountByIban.get().getIndividualId());
            depositAccountByIban.get().setIndividualDTO(individualDTO);
            return ResponseEntity.ok(depositAccountByIban.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "account-deposit/{individualId}")
    public ResponseEntity<List<AccountDepositDTO>> retrieveDepositIndividual(@PathVariable("individualId") int individualId) {

        List<AccountDepositDTO> depositsAccountsAllByIndividualId = depositAccountService.getAllByIndividualId(individualId);

        if (depositsAccountsAllByIndividualId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        IndividualDTO individualDTO = individualRestClient.getIndividualById(individualId);
        for (AccountDepositDTO depositAccount: depositsAccountsAllByIndividualId) {
            depositAccount.setIndividualDTO(individualDTO);
        }
        return ResponseEntity.ok(depositsAccountsAllByIndividualId);
    }

    @PostMapping(value = "/create-account-deposit/individual/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDepositDTO> createAccountDepositForIndividual(@PathVariable("id") int id, @RequestBody ArgsDTO argsDTO) {
        AccountDepositDTO individualAccountDeposit = depositAccountService.createIndividualAccountDeposit(id, argsDTO.getMonths(), argsDTO.getAmount());
        IndividualDTO individualDTO = individualRestClient.getIndividualById(id);

        individualAccountDeposit.setIndividualDTO(individualDTO);

        return ResponseEntity.ok(individualAccountDeposit);
    }
    @PatchMapping(path = "/account-deposit/debit/{iban}")
    public ResponseEntity<AccountDepositDTO> debitAccountDeposit(@PathVariable("iban") String iban, @RequestBody CreditAccountDepositDTO amount) {

        Optional<AccountDepositDTO> depositAccountDTOByIban = depositAccountService.getByIban(iban);

        if (depositAccountDTOByIban.isPresent()){
            AccountDepositDTO accountDepositDTO = depositAccountDTOByIban.get();
            IndividualDTO individualDTO = individualRestClient.getIndividualById(depositAccountDTOByIban.get().getIndividualId());

            accountDepositDTO.setIndividualDTO(individualDTO);
            LocalDate maturityDate = depositAccountDTOByIban.get().getMaturityDate();

            if(maturityDate != null && ! maturityDate.isBefore(LocalDate.now())) {
                depositAccountService.debitBalanceDeposit(accountDepositDTO.getIban(), amount.getAmount());
                return ResponseEntity.ok(accountDepositDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value = "delete/account-deposit/{iban}")
    public void deleteDepositFromRepository(@PathVariable("iban") String iban) {
        depositAccountService.deleteAccountDepositById(iban);
    }


}
