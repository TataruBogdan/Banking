package com.intecbrussel.bankingscheduler.rest.client;

import com.intecbrussel.commonsservice.dto.AccountCurrentDTO;
import com.intecbrussel.commonsservice.dto.AmountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountCurrentRestClient {

    @Autowired
    private RestTemplate currentRestTemplate;

    public AccountCurrentDTO getAccountCurrentByIban(String iban) {
        return currentRestTemplate.getForObject("http://localhost:8200/accounts-currents" + iban,
                AccountCurrentDTO.class);
    }

    public ResponseEntity<AccountCurrentDTO> creditAccountCurrent (String iban, Double amount){
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(amount);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AmountDTO> requestEntity = new HttpEntity<>(amountDTO, httpHeaders);

        ResponseEntity<AccountCurrentDTO> accountCurrentDTOResponseEntity = currentRestTemplate
                .exchange("http://localhost:8200/account-current/credit" + iban,
                HttpMethod.PATCH, requestEntity, AccountCurrentDTO.class);

        return accountCurrentDTOResponseEntity;
    }

    public ResponseEntity<AccountCurrentDTO> debitAccountCurrent(String iban, Double amount) {

        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(amount);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AmountDTO> requestEntity = new HttpEntity<>(amountDTO, httpHeaders);

        ResponseEntity<AccountCurrentDTO> accountCurrentDTOResponseEntity = currentRestTemplate
                .exchange("http://localhost:8200/account-current/debit" + iban,
                HttpMethod.PATCH, requestEntity, AccountCurrentDTO.class);

        return accountCurrentDTOResponseEntity;

    }
}
