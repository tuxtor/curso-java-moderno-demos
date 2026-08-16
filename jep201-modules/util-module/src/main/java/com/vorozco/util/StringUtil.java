package com.vorozco.util;

public class StringUtil {

    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static String toUpper(String input) {
        return input.toUpperCase();
    }
}
