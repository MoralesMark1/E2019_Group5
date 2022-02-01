package com.group5.finalproject;

import java.util.Random;

public class RandomString {

    private final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private final String NUMBERS = "0123456789";
    private final char [] ALPHANUMERIC = (LETTERS+LETTERS.toUpperCase()+NUMBERS).toCharArray();

    // class to generate random link
    public String generateAlphaNumeric(int length){
        StringBuilder result = new StringBuilder();

        for (int i = 0; i<length; i++){
            result.append(ALPHANUMERIC[new Random().nextInt(ALPHANUMERIC.length)]);
        }
        return result.toString();
    }

}
