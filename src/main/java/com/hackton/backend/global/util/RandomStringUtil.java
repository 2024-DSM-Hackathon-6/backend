package com.hackton.backend.global.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomStringUtil {

    public static String generateRandomMixStr(int length, boolean isUpperCase) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        String answer = isUpperCase ? sb.toString() : sb.toString().toLowerCase();

        return answer.substring(10);
    }
}
