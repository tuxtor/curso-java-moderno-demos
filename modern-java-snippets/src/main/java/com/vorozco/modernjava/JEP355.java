package com.vorozco.modernjava;

public class JEP355 {

    public static void main(String[] args) {
        System.out.println(oldMultiLineString());
        System.out.println("---");
        System.out.println(newTextBlock());
        System.out.println("---");

        System.out.println(oldStringWithQuotes());
        System.out.println(newTextBlockWithQuotes());

        System.out.println(oldFormattedString("Copilot"));
        System.out.println(newFormattedTextBlock("Copilot"));
    }

    private static String oldMultiLineString() {
        return "Line one\n" +
                "Line two\n" +
                "Line three";
    }

    private static String newTextBlock() {
        return """
                Line one
                Line two
                Line three""";
    }

    private static String oldStringWithQuotes() {
        return "He said \"Hello, world!\" and left.";
    }

    private static String newTextBlockWithQuotes() {
        return """
                He said "Hello, world!" and left.""";
    }

    private static String oldFormattedString(String name) {
        return String.format("Hello, %s!%nWelcome to JEP 355.", name);
    }

    private static String newFormattedTextBlock(String name) {
        return """
                Hello, %s!
                Welcome to JEP 355.""".formatted(name);
    }
}
