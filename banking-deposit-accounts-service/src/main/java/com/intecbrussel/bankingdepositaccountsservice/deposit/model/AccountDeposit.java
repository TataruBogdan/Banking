package com.intecbrussel.bankingdepositaccountsservice.deposit.model;

import com.intecbrussel.commonsservice.dto.types.DepositStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="account_deposit")
public class AccountDeposit {
    @Id
    @Column(name = "iban")
    private String iban;
    @Column(name = "deposit_amount")
    private double depositAmount;
    @Column(name = "balance")
    private double balance;
    @Column(name = "individual_id")
    private int individualId;
    @Column(name = "interest_rate")
    private double interestRate;
    @Column(name = "maturity_date")
    private LocalDate maturityDate;
    @Column(name = "maturity_months")
    private int maturityMonths;
    @Column(name = "self_capitalization")
    private boolean selfCapitalization;
    @Column(name = "maturity_iban")
    private String maturityIban;
    @Column(name = " start_date")
    private LocalDateTime startDate;
    @Column(name = "status")
    private DepositStatus status;

}
