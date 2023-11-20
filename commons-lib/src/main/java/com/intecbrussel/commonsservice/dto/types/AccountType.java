package com.intecbrussel.commonsservice.dto.types;

public enum AccountType {

    CURRENT("CURR", "current-account-service");

    private final String ibanType;
    private final String microservice;

    AccountType(String ibanType, String microservice) {
        this.ibanType = ibanType;
        this.microservice = microservice;
    }




}
