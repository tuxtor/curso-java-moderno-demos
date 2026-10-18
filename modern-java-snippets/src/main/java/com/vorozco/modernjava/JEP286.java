package com.vorozco.modernjava;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class JEP286 {

  public static void main(String[] args) {
    System.out.println(oldPrimitiveDeclaration());
    System.out.println(newPrimitiveDeclaration());

    System.out.println(oldObjectDeclaration());
    System.out.println(newObjectDeclaration());

    System.out.println(oldForEachLoop());
    System.out.println(newForEachLoop());

    System.out.println(oldTryWithResources());
    System.out.println(newTryWithResources());
  }

  private static String oldPrimitiveDeclaration() {
    int count = 42;
    return "count = " + count;
  }

  private static String newPrimitiveDeclaration() {
    var count = 42;
    return "count = " + count;
  }

  private static String oldObjectDeclaration() {
    String message = "Hello, JEP 286!";
    return message;
  }

  private static String newObjectDeclaration() {
    var message = "Hello, JEP 286!";
    return message;
  }

  private static String oldForEachLoop() {
    List<String> languages = List.of("Java", "Kotlin", "Scala");
    StringBuilder result = new StringBuilder();
    for (String language : languages) {
      result.append(language).append(" ");
    }
    return result.toString().trim();
  }

  private static String newForEachLoop() {
    var languages = List.of("Java", "Kotlin", "Scala");
    var result = new StringBuilder();
    for (var language : languages) {
      result.append(language).append(" ");
    }
    return result.toString().trim();
  }

  private static String oldTryWithResources() {
    try (StringReader reader = new StringReader("data")) {
      int firstChar = reader.read();
      return "First char: " + (char) firstChar;
    } catch (IOException e) {
      return "Error: " + e.getMessage();
    }
  }

  private static String newTryWithResources() {
    try (var reader = new StringReader("data")) {
      int firstChar = reader.read();
      return "First char: " + (char) firstChar;
    } catch (IOException e) {
      return "Error: " + e.getMessage();
    }
  }
}
