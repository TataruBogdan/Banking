package com.intecbrussel.bankingcurrentaccountsservice.account.controller;
import com.intecbrussel.commonsservice.dto.AccountCurrentDTO;
import com.intecbrussel.commonsservice.dto.CreditAccountCurrentDTO;
import com.intecbrussel.commonsservice.dto.DebitAccountCurrentDTO;
import com.intecbrussel.bankingcurrentaccountsservice.account.controller.client.IndividualRestClient;
import com.intecbrussel.bankingcurrentaccountsservice.account.service.AccountCurrentService;
import com.intecbrussel.bankingcurrentaccountsservice.account.dto.UpdateBalanceRequestDTO;
import com.intecbrussel.commonsservice.dto.IndividualDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
public class AccountCurrentController {

    @Autowired
    private final AccountCurrentService accountCurrentService;

    @Autowired
    private IndividualRestClient individualRestClient;

    @GetMapping("/accounts-current")
    public ResponseEntity<List<AccountCurrentDTO>> retrieveAllAccounts() {

        List<AccountCurrentDTO> allAccountsCurrentDTO = accountCurrentService.getAll();
        if (allAccountsCurrentDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<AccountCurrentDTO> newAccountCurrentDTOList = new ArrayList<>();
        for (AccountCurrentDTO accountCurrentDTO: allAccountsCurrentDTO) {
            IndividualDTO individualById = individualRestClient.getIndividualById(accountCurrentDTO.getIndividualId());
            accountCurrentDTO.setIndividual(individualById);
            newAccountCurrentDTOList.add(accountCurrentDTO);
        }
        return ResponseEntity.ok(newAccountCurrentDTOList);

    }

    @GetMapping("/accounts-current/{iban}")
    public ResponseEntity<AccountCurrentDTO> retrieveAccountCurrent(@PathVariable String iban){
        Optional<AccountCurrentDTO> accountCurrentByIban = accountCurrentService.getByIban(iban);

        if (accountCurrentByIban.isPresent()) {
            IndividualDTO individualDTO = individualRestClient.getIndividualById(accountCurrentByIban.get().getIndividualId());
            accountCurrentByIban.get().setIndividual(individualDTO);
            return ResponseEntity.ok(accountCurrentByIban.get()); // return 200, with json body
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
    }

    @PatchMapping(path = "/account-current/update-balance/{iban}")
//    @ResponseBody - return type is String - not needed
    public ResponseEntity<AccountCurrentDTO> updateAccountCurrentBalance(@PathVariable("iban") String iban, @RequestBody UpdateBalanceRequestDTO amount){

        AccountCurrentDTO accountCurrentDTO = accountCurrentService.updateBalanceAccount(iban, amount.getAmount());

        return ResponseEntity.ok(accountCurrentDTO);
    }

    @PatchMapping(path = "/account-current/credit/{iban}")
    public ResponseEntity<AccountCurrentDTO> creditedAccountCurrent(@PathVariable("iban") String iban, @RequestBody CreditAccountCurrentDTO amount){

        Optional<AccountCurrentDTO> accountCurrentDTO = accountCurrentService.getByIban(iban);


        IndividualDTO individualDTOById = individualRestClient.getIndividualById(accountCurrentDTO.get().getIndividualId());

        String fromIban = accountCurrentDTO.get().getIban();
        //credit the value from AccountCurrent with the value from transaction -> go to debit
        AccountCurrentDTO creditBalanceAccount = accountCurrentService.creditBalanceAccount(iban, amount.getAmount());
        creditBalanceAccount.setIndividual(individualDTOById);

        return ResponseEntity.ok(creditBalanceAccount);
    }

    //Payment from account                                                                           // change requestBody AmountDTO ???
    @PatchMapping(path = "/account-current/debit/{iban}")
    public ResponseEntity<AccountCurrentDTO> debitedAccountCurrent(@PathVariable("iban") String iban, @RequestBody DebitAccountCurrentDTO amount){

        Optional<AccountCurrentDTO> accountCurrentDTO = accountCurrentService.getByIban(iban);
        IndividualDTO individualById = individualRestClient.getIndividualById(accountCurrentDTO.get().getIndividualId());

        //debit the value from AccountCurrent with the value from transaction                     // amount from AmountDTO
        AccountCurrentDTO debitAccountCurrentDTO = accountCurrentService.debitBalanceAccount(iban, amount.getAmount());
        debitAccountCurrentDTO.setIndividual(individualById);

        return ResponseEntity.ok(debitAccountCurrentDTO);
    }

    @GetMapping(value = "/account-current/{individualId}")
    public ResponseEntity<List<AccountCurrentDTO>> retrieveAccountIndividual(@PathVariable("individualId") int individualId){

        List<AccountCurrentDTO> accountCurrentServiceByIndividual = accountCurrentService.getByIndividualId(individualId);

        if (accountCurrentServiceByIndividual.isEmpty()){

            return ResponseEntity.notFound().build();
        }
        IndividualDTO individualDTO = individualRestClient.getIndividualById(individualId);
        for (AccountCurrentDTO accountCurrent: accountCurrentServiceByIndividual) {
            accountCurrent.setIndividual(individualDTO);
        }
        return ResponseEntity.ok(accountCurrentServiceByIndividual);
    }

    @PostMapping(value = "/create-account/individuals/{individualId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE )
    public ResponseEntity<AccountCurrentDTO> createAccountCurrent(@PathVariable("individualId") int individualId){

        AccountCurrentDTO individualAccount = accountCurrentService.createIndividualAccount(individualId);
        IndividualDTO individualDTOById = individualRestClient.getIndividualById(individualId);

        individualAccount.setIndividual(individualDTOById);

        return ResponseEntity.ok(individualAccount);
    }

    @DeleteMapping(value = "delete/account-current/{iban}")
    public void deleteAccountFromRepository(@PathVariable("iban") String iban){

        accountCurrentService.deleteAccountByIban(iban);
    }
}
