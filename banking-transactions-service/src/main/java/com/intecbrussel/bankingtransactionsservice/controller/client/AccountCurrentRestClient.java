package com.intecbrussel.bankingtransactionsservice.controller.client;

import com.intecbrussel.bankingtransactionsservice.config.ApplicationProperties;
import com.intecbrussel.commonsservice.dto.AccountCurrentDTO;
import com.intecbrussel.commonsservice.dto.CreditAccountCurrentDTO;
import com.intecbrussel.commonsservice.dto.DebitAccountCurrentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountCurrentRestClient {

    @Autowired
    private RestTemplate currentRestTemplate;
    @Autowired
    private ApplicationProperties applicationProperties;

    public AccountCurrentDTO getAccountCurrentByIban(String iban) {
        AccountCurrentDTO accountCurrentDTO = currentRestTemplate.getForObject(
                applicationProperties.getAccountCurrentUrl() + iban, AccountCurrentDTO.class);
        return accountCurrentDTO;
    }

    //PATCH mapping in AccountCurrentController -
    //http://localhost:8200/accounts-current/
    public ResponseEntity<AccountCurrentDTO> debitAccountCurrent(String iban, Double amount) {

        //String body = "{\"fromIban\":\"CURR-\",\"amount\":amount}";
        DebitAccountCurrentDTO debitAccountCurrentDTO = new DebitAccountCurrentDTO();
        debitAccountCurrentDTO.setAmount(amount);

        // HttpHeaders are created and set to specify that the request body is in JSON format.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity is created as the request body and the HttpHeaders.
        HttpEntity<DebitAccountCurrentDTO> requestEntity = new HttpEntity<>(debitAccountCurrentDTO, httpHeaders);

        /*The RestTemplate's exchange method is used to make an HTTP PATCH request to the specified URL.
        * The URL is constructed by appending the provided iban to the base URL.
        * The HTTP method is set to PATCH.
        * The requestEntity contains the request body and headers.
        * The response is expected to be of type AccountCurrentDTO. */

        ResponseEntity<AccountCurrentDTO> accountCurrentFromIbanDTOResponseEntity =
                currentRestTemplate.exchange("http://localhost:8200/account-current/debit/" + iban,
                        HttpMethod.PATCH,requestEntity, AccountCurrentDTO.class);
        return accountCurrentFromIbanDTOResponseEntity;
    }

    public ResponseEntity<AccountCurrentDTO> creditAccountCurrent(String iban, Double amount) {

        //String body = "{\"toIban\":\"CURR-\",\"amount\":amount}";
        CreditAccountCurrentDTO creditAccountCurrentDTO = new CreditAccountCurrentDTO();
        creditAccountCurrentDTO.setAmount(amount);

        // HttpHeaders are created and set to specify that the request body is in JSON format.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity is created as the request body and the HttpHeaders.
        HttpEntity<CreditAccountCurrentDTO> requestEntity = new HttpEntity<>(creditAccountCurrentDTO, httpHeaders);

        ResponseEntity<AccountCurrentDTO> accountCurrentToIbanDTOResponseEntity =
                currentRestTemplate.exchange("http://localhost:8200/account-current/credit/" + iban,
                        HttpMethod.PATCH, requestEntity, AccountCurrentDTO.class);
        return accountCurrentToIbanDTOResponseEntity;
    }


}
