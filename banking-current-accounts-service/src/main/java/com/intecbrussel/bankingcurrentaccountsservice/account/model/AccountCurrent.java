package com.intecbrussel.bankingcurrentaccountsservice.account.model;

import com.intecbrussel.commonsservice.dto.types.CurrentStatus;
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
    // do I need aldo account_type ????
    @Column(name = "balance")
    private Double balance;
    @Column(name = "individual_id")
    private Integer individualId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_current_status")
    private CurrentStatus currentStatus;
    @Column(name = "primary_account")
    private boolean primaryAccount;
}
