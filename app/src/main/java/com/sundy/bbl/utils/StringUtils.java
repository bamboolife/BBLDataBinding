package com.sundy.bbl.utils;

public class StringUtils {
    public static String capitalize(final String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }

    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }
}
