package com.vorozco.modernjava;

public class JEP406 {

  public static void main(String[] args) {
    Object[] inputs = { 42, "Hi", "Hello, JEP 406!", null };

    for (Object input : inputs) {
      System.out.println("(old) " + oldDescribe(input));
      System.out.println("(new) " + newDescribe(input));
    }
  }

  private static String oldDescribe(Object obj) {
    if (obj == null) {
      return "it's null";
    } else if (obj instanceof Integer) {
      Integer i = (Integer) obj;
      return "an Integer: " + i;
    } else if (obj instanceof String) {
      String s = (String) obj;
      if (s.length() > 5) {
        return "a long String: " + s;
      }
      return "a short String: " + s;
    } else {
      return "something else";
    }
  }

  private static String newDescribe(Object obj) {

    return switch (obj) {
      case Integer i -> "an Integer: " + i;
      case String s when s.length() > 5 -> "an long String: " + s;
      case String s -> "an String: " + s;
      case null -> "it is a null";
      default -> "something else";
    };

  }

}
