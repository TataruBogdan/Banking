package com.intecbrussel.commonsservice.dto;

import com.intecbrussel.commonsservice.dto.types.CurrentStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AccountCurrentDTO {

    @NotNull
    private String iban;
    private Double balance;
    private Integer individual;
    private LocalDateTime startDate;
    private CurrentStatus currentStatus;
    private boolean primaryAccount;
    private IndividualDTO individualDTO;
}
