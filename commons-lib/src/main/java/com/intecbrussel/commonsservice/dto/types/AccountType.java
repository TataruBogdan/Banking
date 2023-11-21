package com.intecbrussel.commonsservice.dto.types;

public enum AccountType {

    CURRENT("CURR", "banking-current-accounts-service"),
    DEPOSIT("DEP", "banking-deposit-accounts-service");

    private final String ibanType;
    private final String microservice;

    AccountType(String ibanType, String microservice) {
        this.ibanType = ibanType;
        this.microservice = microservice;
    }




}
