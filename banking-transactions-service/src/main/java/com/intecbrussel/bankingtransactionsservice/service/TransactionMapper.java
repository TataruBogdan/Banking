package com.intecbrussel.bankingtransactionsservice.service;

import com.intecbrussel.bankingtransactionsservice.model.Transaction;
import com.intecbrussel.commonsservice.dto.TransactionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toTransaction(TransactionDTO transactionDTO);

    TransactionDTO transactionToDTO(Transaction transaction);

    List<TransactionDTO> listTransactionDto(List<Transaction> transactionList);
}
