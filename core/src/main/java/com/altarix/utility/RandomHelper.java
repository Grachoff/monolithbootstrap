package com.altarix.utility;

import java.util.Random;

public class RandomHelper {

    public static String createRandomString(int len) {
        StringBuilder builderForId = new StringBuilder(len);
        Random random = new Random();
        while (builderForId.length() < len) {
            String part = String.valueOf(Math.abs(random.nextLong()));
            if ((len - builderForId.length()) >= part.length()) builderForId.append(part);
            else {
                builderForId.append(part.substring(0,len - builderForId.length()));
                break;
            }
        }
        return builderForId.toString();
    }
}
