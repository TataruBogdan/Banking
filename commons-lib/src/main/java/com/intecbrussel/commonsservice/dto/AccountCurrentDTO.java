package com.intecbrussel.commonsservice.dto;

import com.intecbrussel.commonsservice.dto.types.CurrentStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AccountCurrentDTO {

    @NotNull
    private String iban;
    private Double balance;
    private Integer individualId;
    private LocalDate startDate;
    private CurrentStatus currentStatus;
    private boolean primaryAccount;
    private IndividualDTO individual;

}
