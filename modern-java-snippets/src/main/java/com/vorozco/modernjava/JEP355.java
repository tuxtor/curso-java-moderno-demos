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

  static String oldMultiLineString() {
    return "Line one\n" +
        "Line two\n" +
        "Line three";
  }

  static String newMultiLineString() {
    return """
        Line one
        Line two
        Line three
        """;

  }

  static String oldStringWithQuotes() {
    return "He said \"Hello, world!\" and left.";
  }

  static String newStringWithQuotes() {
    return """
          He said "Hello, world!" and left.
        """;
  }

  static String oldFormattedString(String name) {
    return String.format("Hello, %s!%nWelcome to JEP 355.", name);
  }

  static String newFormattedString(String name) {
    return """
        Hello, %s!
        Welcome to JEP 355.""".formatted(name);
  }
}
