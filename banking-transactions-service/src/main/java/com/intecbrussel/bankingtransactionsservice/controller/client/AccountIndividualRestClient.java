package com.intecbrussel.bankingtransactionsservice.controller.client;

import com.intecbrussel.bankingtransactionsservice.config.ApplicationProperties;
import com.intecbrussel.commonsservice.dto.IndividualDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountIndividualRestClient {

    @Autowired
    private RestTemplate individualRestTemplate;
    @Autowired
    private ApplicationProperties applicationProperties;

    public IndividualDTO getIndividualById(Integer id) {
        IndividualDTO individualDTO = individualRestTemplate.getForObject(
                applicationProperties.getIndividualUrl() + id, IndividualDTO.class
        );
        return individualDTO;
    }


}

