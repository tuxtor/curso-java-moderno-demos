package com.vorozco.modernjava;

public class JEP355 {

    public static void main(String[] args) {
        System.out.println(oldMultiLineString());
        System.out.println("---");

        System.out.println(oldStringWithQuotes());

        System.out.println(oldFormattedString("Copilot"));
    }

    private static String oldMultiLineString() {
        return "Line one\n" +
                "Line two\n" +
                "Line three";
    }

    private static String oldStringWithQuotes() {
        return "He said \"Hello, world!\" and left.";
    }

    private static String oldFormattedString(String name) {
        return String.format("Hello, %s!%nWelcome to JEP 355.", name);
    }
}
