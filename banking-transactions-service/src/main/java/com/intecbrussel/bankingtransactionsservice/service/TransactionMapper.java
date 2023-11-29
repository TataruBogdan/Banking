package com.intecbrussel.bankingtransactionsservice.service;

import com.intecbrussel.bankingtransactionsservice.model.Transaction;
import com.intecbrussel.commonsservice.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    // added like this is correct ?
    @Mapping(target = "toIndividualId", source = "fromIndividualId")
    Transaction toTransaction(TransactionDTO transactionDTO);


    TransactionDTO transactionToDTO(Transaction transaction);

    List<TransactionDTO> listTransactionDto(List<Transaction> transactionList);
}
