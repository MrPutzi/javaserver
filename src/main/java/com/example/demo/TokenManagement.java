package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class TokenManagement {

//method for generating a string of random characters
    public static String generateToken() {
        int length = 10;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (chars.length() * Math.random());
            token.append(chars.charAt(index));
        }
        return token.toString();
    }

    //delete token

//method for checking if the token is valid
    public static boolean isTokenValid(String token) {
        if (token.length() != 10) {
            return false;
        }
        for (int i = 0; i < token.length(); i++) {
            if (!Character.isLetterOrDigit(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
