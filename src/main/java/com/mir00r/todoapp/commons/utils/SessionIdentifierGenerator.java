package com.mir00r.todoapp.commons.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

    public String nextPassword(){
        return new BigInteger(130, random).toString(32);
//        SecureRandom random = new SecureRandom();
//        return "p"+(10000+random.nextInt(9000000));
    }

    public static int generateOTP() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(999999);
    }

}
