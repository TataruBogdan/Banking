package com.intecbrussel.bankingcurrentaccountsservice.account.controller.client;

import com.intecbrussel.commonsservice.dto.IndividualDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IndividualRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public IndividualDTO getIndividualById(Integer id) {

        return restTemplate.getForObject("http://localhost:8100/individuals/" + id, IndividualDTO.class);
    }
}
