package com.example.backend.utils;

import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

public class PasswordUtil {
    private static final BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
    private static final String pepper = System.getProperty("PEPPER_SECRET");

    public static String hashPassword(String plainPassword) {
        return Password.hash(plainPassword)
                .addPepper(pepper)
                .with(bcrypt)
                .getResult();
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return Password.check(plainPassword, hashedPassword)
                .addPepper(pepper)
                .with(bcrypt);
    }
}
