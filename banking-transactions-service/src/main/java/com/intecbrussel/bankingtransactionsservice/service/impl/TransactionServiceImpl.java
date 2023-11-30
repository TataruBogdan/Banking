package com.intecbrussel.bankingtransactionsservice.service.impl;

import com.intecbrussel.bankingtransactionsservice.dao.TransactionRepository;
import com.intecbrussel.bankingtransactionsservice.idGen.IbanUtils;
import com.intecbrussel.bankingtransactionsservice.model.Transaction;
import com.intecbrussel.bankingtransactionsservice.service.TransactionMapper;
import com.intecbrussel.bankingtransactionsservice.service.TransactionService;
import com.intecbrussel.commonsservice.dto.TransactionDTO;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionDTO createTransaction(String fromIban, String toIban, Integer fromId, Integer toId, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setFromIban(fromIban);
        transaction.setFromAccountType(IbanUtils.parseTypeStringIban(fromIban));
        transaction.setFromIndividualId(fromId);
        transaction.setToIban(toIban);
        transaction.setToAccountType(IbanUtils.parseTypeStringIban(toIban));
        transaction.setToIndividualId(toId);
        transaction.setTransactionAmount(amount);
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.NEW);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return  transactionMapper.transactionToDTO(savedTransaction);
    }

    public Optional<TransactionDTO> getTransactionById(String transactionId) {
        Optional<Transaction> transactionRepositoryById = transactionRepository.findById(transactionId);
        return transactionRepositoryById.map(transaction -> transactionMapper.transactionToDTO(transaction));
    }

    @Override
    public List<String> getAllTransactionsFindTransactionsStatus(List<TransactionStatus> statuses) {
        List<String> stringStatusesToList = statuses.stream()
                .map(Enum::toString)
                .collect(Collectors.toList());

        return transactionRepository.findTransactionByTransactionStatusList(stringStatusesToList);
    }

    @Override
    public Optional<TransactionDTO> updateTransactionIdTransactionStatus(String transactionId, TransactionStatus status) {
        Optional<Transaction> transactionRepositoryById = transactionRepository.findById(transactionId);

        if (transactionRepositoryById.isPresent()){
            transactionRepositoryById.get().setStatus(TransactionStatus.FINISHED);
            Transaction savedTransaction = transactionRepository.save(transactionRepositoryById.get());
            return Optional.ofNullable(transactionMapper.transactionToDTO(savedTransaction));
        } else {
            return Optional.empty();
        }
    }


}
