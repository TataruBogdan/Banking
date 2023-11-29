package com.intecbrussel.bankingscheduler.rest.client;

import com.intecbrussel.commonsservice.dto.SearchTransactionsResponseDTO;
import com.intecbrussel.commonsservice.dto.TransactionDTO;
import com.intecbrussel.commonsservice.dto.TransactionSearchInputDTO;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionRestClient {

    @Autowired
    private RestTemplate transactionRestTemplate;


    public ResponseEntity<TransactionDTO> createTransaction(String fromIban, String toIban, Double amount) {

        String body = "{\"fromIban\":\"fromIban\",\"toIban\":\"toIban\"}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);


        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("fromIban", fromIban);
        urlVariables.put("toIban", toIban);

        ResponseEntity<TransactionDTO> transactionDTOResponseEntity = transactionRestTemplate
                .postForEntity("http://localhost:8500/transactions/fromIban/{fromIban}/toIban/{toIban}" + amount,
                requestEntity, TransactionDTO.class, urlVariables);

        return transactionDTOResponseEntity;
    }

    public ResponseEntity<List<String>> getAllTransactionsWithStatus(List<TransactionStatus> statusList) {

        TransactionSearchInputDTO searchInputDTO = new TransactionSearchInputDTO();

        searchInputDTO.setStatusList(statusList);

        //Set Headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Accept", "application/json");

        // set the request body
        Map<String, Object> map = new HashMap<>();
        map.put("statusList", statusList);

        ResponseEntity<SearchTransactionsResponseDTO> exchange = transactionRestTemplate
                .postForEntity("http://localhost:8500/transactions/search-by-status",
                HttpMethod.POST, SearchTransactionsResponseDTO.class, map);

        return ResponseEntity.ok(exchange.getBody().getIds());

    }

    public ResponseEntity<Void> executeTransaction(String transactionId) {

        String url = "http://localhost:8500/execute/{transactionId}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, String> urlVariable = new HashMap<>();
        urlVariable.put("transactionId", transactionId);

        ResponseEntity<Void> exchange = transactionRestTemplate.exchange(url, HttpMethod.PATCH, new HttpEntity<>(httpHeaders), Void.class, urlVariable);

        return exchange;
    }

}
