package com.intecbrussel.bankingloansservice.loan.model;


import com.intecbrussel.commonsservice.dto.types.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account_loan")
@Entity
public class AccountLoan {

    @Id
    @Column(name = "iban")
    private String iban;
    @Column(name = "loan_amount")
    private double loanAmount;
    @Column(name = "individual_id")
    private int individualId;
    @Column(name = "period", nullable = false)
    private int period;
    @Column(name = "interest_rate")
    private double interestRate;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status")
    private LoanStatus loanStatus;
    // The initial amount borrowed, which decrease as payments are made.
    @Column(name = "principal")
    private double principal;
}
