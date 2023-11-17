package com.intecbrussel.bankingcurrentaccountsservice.account.model;

import com.intecbrussel.bankingcurrentaccountsservice.account.dto.types.CurrentStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account_current")
@Entity
public class AccountCurrent {

    @Id
    @NotNull
    @Column(name = "iban", unique = true)
    private String iban;
    private Double balance;
    private Integer individualId;
    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
    private CurrentStatus currentStatus;
    private boolean primaryAccount;
}
