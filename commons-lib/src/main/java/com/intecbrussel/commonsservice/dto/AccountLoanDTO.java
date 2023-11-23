package com.intecbrussel.commonsservice.dto;

import com.intecbrussel.commonsservice.dto.types.LoanStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountLoanDTO {

    private String iban;
    private double loanAmount;
    private int individualId;
    private int period;
    private double interestRate;
    private LocalDateTime startDate;
    private LoanStatus loanStatus;
    private double principal;
    private IndividualDTO individualDTO;
}
