package com.vorozco.modernjava;

public class JEP355 {

  public static void main(String[] args) {
    System.out.println(oldMultiLineString());
    System.out.println(newMultiLineString());
    System.out.println("---");

    System.out.println(oldStringWithQuotes());
    System.out.println(newStringWithQuotes());

    System.out.println(oldFormattedString("Copilot"));
    System.out.println(newFormattedString("Copilot"));
  }

  private static String oldMultiLineString() {
    return "Line one\n" +
        "Line two\n" +
        "Line three";
  }

  private static String newMultiLineString() {
    return """
        Line one
        Line two
        Line three
        """;

  }

  private static String oldStringWithQuotes() {
    return "He said \"Hello, world!\" and left.";
  }

  private static String newStringWithQuotes() {
    return """
          He said "Hello, world!" and left.
        """;
  }

  private static String oldFormattedString(String name) {
    return String.format("Hello, %s!%nWelcome to JEP 355.", name);
  }

  private static String newFormattedString(String name) {
    return """
        Hello, %s!
        Welcome to JEP 355.""".formatted(name);
  }
}
