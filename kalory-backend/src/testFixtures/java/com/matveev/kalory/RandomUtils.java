package com.matveev.kalory;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

public interface RandomUtils {

    SecureRandom RANDOM = new SecureRandom();

    static Long aRandomId() {
        return RANDOM.nextLong();
    }

    static String aRandomString(int length) {
        return RandomStringUtils.random(length);
    }
}
