package com.intecbrussel.bankingtransactionsservice.service;

import com.intecbrussel.commonsservice.dto.TransactionDTO;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    TransactionDTO createTransaction(
            String fromIban,
            String toIban,
            Integer fromId,
            Integer toId,
            Double amount);

    Optional<TransactionDTO> getTransactionById(String transactionId);

    List<String> getAllTransactionsFindTransactionsStatus(List<TransactionStatus> statuses);
}
