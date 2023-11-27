package com.intecbrussel.bankingtransactionsservice.controller.client;

import com.intecbrussel.bankingtransactionsservice.config.ApplicationProperties;
import com.intecbrussel.commonsservice.dto.AccountLoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountLoanRestClient {
    @Autowired
    private RestTemplate loanRestTemplate;

    @Autowired
    private ApplicationProperties applicationProperties;

    public AccountLoanDTO getAccountLoanByIban(String iban) {

        AccountLoanDTO accountLoanDTO = loanRestTemplate.getForObject(
                applicationProperties.getAccountsLoanUrl() + iban, AccountLoanDTO.class
        );

        return accountLoanDTO;
    }
}
