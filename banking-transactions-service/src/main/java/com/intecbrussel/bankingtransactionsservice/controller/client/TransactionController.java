package com.intecbrussel.bankingtransactionsservice.controller.client;

import com.intecbrussel.bankingtransactionsservice.service.TransactionService;
import com.intecbrussel.commonsservice.dto.*;
import com.intecbrussel.commonsservice.dto.types.AccountType;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.intecbrussel.bankingtransactionsservice.idGen.IbanUtils.parseTypeStringIban;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RequiredArgsConstructor
@RestControllerAdvice
@RequestMapping("/transactions")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountIndividualRestClient accountIndividualRestClient;

    @Autowired
    private AccountCurrentRestClient accountCurrentRestClient;

    @Autowired
    private AccountDepositRestClient accountDepositRestClient;

    @Autowired
    private AccountLoanRestClient accountLoanRestClient;

    @PostMapping("/fromIban/{fromIban}/toIban/{toIban}")
    public ResponseEntity<TransactionDTO> createTransaction(
            @PathVariable("fromIban") String fromIban,
            @PathVariable("toIban") String toIban,
            @RequestBody AmountDTO amount) {

        IndividualDTO fromIndividualDTO = null;
        IndividualDTO toIndividualDTO = null;

        switch (parseTypeStringIban(fromIban)) {
            case CURRENT: {
                AccountCurrentDTO accountCurrentByIban = accountCurrentRestClient.getAccountCurrentByIban(fromIban);
                fromIndividualDTO = accountCurrentByIban.getIndividual();
                break;
            }
            case DEPOSIT: {
                AccountDepositDTO accountDepositByIban = accountDepositRestClient.getAccountDepositByIban(fromIban);
                fromIndividualDTO = accountDepositByIban.getIndividualDTO();
            }
            case LOAN: {
                AccountLoanDTO accountLoanByIban = accountLoanRestClient.getAccountLoanByIban(fromIban);
                fromIndividualDTO = accountLoanByIban.getIndividualDTO();
            }
            default: {
                // Handle default case if needed, currently returns null
                return null;
            }
        }

        switch (parseTypeStringIban(toIban)) {
            case CURRENT: {
                AccountCurrentDTO accountCurrentByIban = accountCurrentRestClient.getAccountCurrentByIban(toIban);
                toIndividualDTO = accountCurrentByIban.getIndividual();
                break;
            }
            case DEPOSIT: {
                AccountDepositDTO accountDepositByIban = accountDepositRestClient.getAccountDepositByIban(toIban);
                toIndividualDTO = accountDepositByIban.getIndividualDTO();
            }
            case LOAN: {
                AccountLoanDTO accountLoanByIban = accountLoanRestClient.getAccountLoanByIban(toIban);
                toIndividualDTO = accountLoanByIban.getIndividualDTO();
            }
            default: {
                return null;
            }
        }
        TransactionDTO transaction = transactionService.createTransaction(
                fromIban, toIban, fromIndividualDTO.getId(), toIndividualDTO.getId(), amount.getAmount());

        transaction.setFromIndividualDTO(fromIndividualDTO);
        transaction.setToIndividualDTO(toIndividualDTO);
        return ResponseEntity.ofNullable(transaction);
    }

    // this will be sent to account service
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> retrieveTransaction(@PathVariable("transactionId") String transactionId) {

        Optional<TransactionDTO> transactionByIBAN = transactionService.getTransactionById(transactionId);

        if (transactionByIBAN.isPresent()) {
            AccountCurrentDTO accountCurrentFromIban = accountCurrentRestClient.getAccountCurrentByIban(transactionByIBAN.get().getFromIban());
            AccountCurrentDTO accountCurrentToIban = accountCurrentRestClient.getAccountCurrentByIban(transactionByIBAN.get().getToIban());

            transactionByIBAN.get().setFromIndividualDTO(accountCurrentFromIban.getIndividual());
            transactionByIBAN.get().setToIndividualDTO(accountCurrentToIban.getIndividual());
            return ResponseEntity.ok(transactionByIBAN.get());
        } else {
             return ResponseEntity.notFound().build();
        }
    }

    //return only id transaction with status
    @PostMapping(value = "/search-by-status", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchTransactionsResponseDTO> getAllTransactionsWithStatus(@RequestBody TransactionSearchInputDTO statuses) {

        List<String> allTransactionsIdsFindTransactionsStatus = transactionService.getAllTransactionsFindTransactionsStatus(statuses.getStatusList());
        SearchTransactionsResponseDTO searchTransactionsResponseDTO = new SearchTransactionsResponseDTO();
        searchTransactionsResponseDTO.setIds(allTransactionsIdsFindTransactionsStatus);

        if (searchTransactionsResponseDTO.getIds().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(searchTransactionsResponseDTO);
    }

    public ResponseEntity<TransactionDTO> executeTransaction(@PathVariable("transactionId") String transactionId) {

        Optional<TransactionDTO> transactionById = transactionService.getTransactionById(transactionId);
        IndividualDTO fromIndividualDTO = transactionById.get().getFromIndividualDTO();
        Integer fromIndividualDTOId = fromIndividualDTO.getId();
        System.out.println(fromIndividualDTOId);

        IndividualDTO toIndividualDTO = transactionById.get().getToIndividualDTO();
        Double transactionAmount = transactionById.get().getTransactionAmount();
        String fromIban = transactionById.get().getFromIban();
        String toIban = transactionById.get().getToIban();

        if (Objects.requireNonNull(parseTypeStringIban(fromIban)) == AccountType.CURRENT) {
            ResponseEntity<AccountCurrentDTO> debitAccountCurrent = accountCurrentRestClient.debitAccountCurrent(fromIban, transactionAmount);
            debitAccountCurrent.getBody().setIndividual(fromIndividualDTO);
        }

        switch (Objects.requireNonNull(parseTypeStringIban(toIban))) {
            case CURRENT: {
                ResponseEntity<AccountCurrentDTO> creditAccountCurrent = accountCurrentRestClient.creditAccountCurrent(toIban, transactionAmount);
                creditAccountCurrent.getBody().setIndividual(toIndividualDTO);
            }
            case DEPOSIT: {
                AccountDepositDTO accountDepositByIban = accountDepositRestClient.getAccountDepositByIban(toIban);
                int maturityMonths = accountDepositByIban.getMaturityMonths();
                accountDepositRestClient.createNewAccountDepositForIndividual(toIndividualDTO, maturityMonths, transactionAmount);
            }
        }

        transactionById.get().setStatus(TransactionStatus.FINISHED);
        return ResponseEntity.status(HttpStatus.OK).body(transactionById.get());


    }

}
