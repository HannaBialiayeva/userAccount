package com.itacademy.userAccount.util;

import java.util.Random;

public class RandomNumberGenerator {

    public static int generateRandomNumber() {
        Random random = new Random();
        int min = 1000;
        int max = 100000;
        return random.nextInt(max - min) + min;
    }
}
