package com.intecbrussel.bankingtransactionsservice.controller.client;

import com.intecbrussel.bankingtransactionsservice.config.ApplicationProperties;
import com.intecbrussel.commonsservice.dto.AccountDepositDTO;
import com.intecbrussel.commonsservice.dto.ArgsDTO;
import com.intecbrussel.commonsservice.dto.IndividualDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class AccountDepositRestClient {

    @Autowired
    private RestTemplate depositRestTemplate;

    @Autowired
    private ApplicationProperties applicationProperties;

    public AccountDepositDTO getAccountDepositByIban(String iban){
        AccountDepositDTO accountDepositDTO = depositRestTemplate.getForObject(
          applicationProperties.getAccountsDepositUrl() + iban, AccountDepositDTO.class
        );
        return accountDepositDTO;
    }


    public ResponseEntity<AccountDepositDTO> createNewAccountDepositForIndividual(IndividualDTO individualId, int months, Double amount) {
        ArgsDTO argsDTO = new ArgsDTO();
        argsDTO.setAmount(amount);
        argsDTO.setMonths(months);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<ArgsDTO> requestEntity = new HttpEntity<>(argsDTO, httpHeaders);

        AccountDepositDTO accountDepositDTOResponseEntity =
                depositRestTemplate.postForObject("/account-debit" + individualId, requestEntity, AccountDepositDTO.class);

        return ResponseEntity.ofNullable(accountDepositDTOResponseEntity);

    }
}
