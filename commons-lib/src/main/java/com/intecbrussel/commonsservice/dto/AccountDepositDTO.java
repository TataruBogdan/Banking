package com.intecbrussel.commonsservice.dto;

import com.intecbrussel.commonsservice.dto.types.DepositStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AccountDepositDTO {

    @NotNull
    private String iban;
    private double depositAmount;
    private double balance;
    private int individualId;
    private double interestRate;
    private LocalDate maturityDate;
    private int maturityMonths;
    private boolean selfCapitalization;
    private String maturityIban;
    private LocalDateTime startDate;
    private DepositStatus status;
    private IndividualDTO individualDTO;


}
