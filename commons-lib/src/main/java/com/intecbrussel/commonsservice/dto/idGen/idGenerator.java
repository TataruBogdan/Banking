package com.intecbrussel.commonsservice.dto.idGen;

import java.util.Random;

public class idGenerator {

    public static String idGen(String stringId) {

        Random randomNumber = new Random();
        long nextLong = randomNumber.nextLong();
        if (nextLong < 0) {
            nextLong = -nextLong;
        }

        return stringId + "-" + nextLong;
    }
}
