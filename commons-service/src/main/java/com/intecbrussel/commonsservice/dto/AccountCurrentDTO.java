package com.intecbrussel.commonsservice.dto;

import com.intecbrussel.commonsservice.dto.types.CurrentStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
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
