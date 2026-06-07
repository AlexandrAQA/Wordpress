package org.example.utils;

import java.util.Random;

public class TestDataGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static String generateRandomTitle() {
        int length = RANDOM.nextInt(10) + 5; // 5 - 15
        return generateRandomString(length);
    }

    public static String generateRandomContent() {
        int length = RANDOM.nextInt(200) + 50; // 50 - 250
        return generateRandomString(length);
    }
}
