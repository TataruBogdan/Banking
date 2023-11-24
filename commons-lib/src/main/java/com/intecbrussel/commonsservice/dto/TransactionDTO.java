package com.intecbrussel.commonsservice.dto;

import com.intecbrussel.commonsservice.dto.types.AccountType;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {


    private String transactionId;
    private int fromIndividualId;
    private String fromIban;
    private AccountType fromAccountType;
    private IndividualDTO fromIndividualDTO;
    private int toIndividualId;
    private String toIban;
    private AccountType toAccountType;
    private IndividualDTO toIndividualDTO;
    private Double transactionAmount;
    private LocalDateTime transactionTime;
    private TransactionStatus status;

}
