package com.vorozco.modernjava;

public class JEP325 {

  public static void main(String[] args) {
    System.out.println(oldSwitchStatement("WEDNESDAY"));
    System.out.println(newSwitchStatement("WEDNESDAY"));

    System.out.println(oldSwitchStatement("SATURDAY"));
    System.out.println(newSwitchStatement("SATURDAY"));

    System.out.println(oldSwitchWithBlockBody("TUESDAY"));
    System.out.println(newSwitchWithBlockBody("TUESDAY"));
  }

  static String oldSwitchStatement(String day) {
    int numLetters;
    switch (day) {
      case "MONDAY":
      case "FRIDAY":
      case "SUNDAY":
        numLetters = 6;
        break;
      case "TUESDAY":
        numLetters = 7;
        break;
      case "THURSDAY":
      case "SATURDAY":
        numLetters = 8;
        break;
      case "WEDNESDAY":
        numLetters = 9;
        break;
      default:
        numLetters = 0;
    }
    return day + " (old) -> " + numLetters;
  }

  static String newSwitchStatement(String day) {
    int numLetters = switch (day) {
      case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
      case "TUESDAY" -> 7;
      case "THURSDAY", "SATURDAY" -> 8;
      case "WEDNESDAY" -> 9;
      default -> 0;
    };
    return day + " (new) -> " + numLetters;
  }

  static String oldSwitchWithBlockBody(String day) {
    String result;
    switch (day) {
      case "TUESDAY": {
        StringBuilder sb = new StringBuilder();
        sb.append(day).append(" is day #2");
        result = sb.toString();
        break;
      }
      default:
        result = day + " is not Tuesday";
    }
    return "(old) " + result;
  }

  static String newSwitchWithBlockBody(String day) {
    String result = switch (day) {
      case "TUESDAY" -> {
        StringBuilder sb = new StringBuilder();
        sb.append(day).append(" is day #2");
        yield sb.toString();
      }
      default -> (day) + " is not Tuesday";
    };
    return "(new) " + result;
  }
}
