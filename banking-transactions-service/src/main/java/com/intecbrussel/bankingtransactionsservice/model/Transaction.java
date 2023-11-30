package com.intecbrussel.bankingtransactionsservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intecbrussel.commonsservice.dto.types.AccountType;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "from_individual_id")
    private Integer fromIndividualId;
    @JsonProperty("fromIban")
    private String fromIban;
    @Enumerated(EnumType.STRING)
    @Column(name = "from_account_type")
    private AccountType fromAccountType;
    @Column(name = "to_individual_id")
    private Integer toIndividualId;
    @JsonProperty("toIban")
    private String toIban;
    @Enumerated(EnumType.STRING)
    @Column(name = "to_account_type")
    private AccountType toAccountType;
    @Column(name = "transaction_amount")
    private Double transactionAmount;
    @Column(name = "transaction_timestamp")
    private LocalDateTime transactionTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus status;

}
