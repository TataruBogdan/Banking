package com.intecbrussel.bankingscheduler.scheduler;

import com.intecbrussel.bankingscheduler.rest.client.AccountCurrentRestClient;
import com.intecbrussel.bankingscheduler.rest.client.TransactionRestClient;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TransactionExecuteScheduler {

    @Autowired
    private AccountCurrentRestClient accountCurrentRestClient;

    @Autowired
    private TransactionRestClient transactionRestClient;


    public void processTransactions() {
        log.info("pick transaction to process");

        // get all transactions to executed
        List<String> transactionsIdsToProcess = (List<String>) transactionRestClient.getAllTransactionsWithStatus(List.of(TransactionStatus.NEW));

        // for each transaction execute it
        for (String transactionsId : transactionsIdsToProcess) {
            transactionRestClient.executeTransaction(transactionsId);
        }
    }
}
