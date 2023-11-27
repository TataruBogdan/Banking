package com.intecbrussel.commonsservice.dto;

import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import lombok.Data;

import java.util.List;

@Data
public class TransactionSearchInputDTO {

    private List<TransactionStatus> statusList;
}
