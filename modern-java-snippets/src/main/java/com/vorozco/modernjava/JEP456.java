package com.vorozco.modernjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JEP456 {

  public static void main(String[] args) {
    System.out.println("(old) Processing key-value pairs:");
    oldForeachLoop();
    System.out.println("(new) Processing key-value pairs:");
    newForeachLoop();
  }

  static void oldForeachLoop() {
    Map<String, Integer> jvmDict = Map.of("Java", 1995, "Kotlin", 2016, "Scala", 2004);
    List<String> languages = new ArrayList<>();
    for (var lang : jvmDict.keySet()) {
      languages.add(lang);
    }
    System.out.println(languages);
  }

  static void newForeachLoop() {
    Map<String, Integer> jvmDict = Map.of("Java", 1995, "Kotlin", 2016, "Scala", 2004);
    List<String> languages = new ArrayList<>();
    jvmDict.forEach((key, _) -> languages.add(key));
    System.out.println(languages);
  }

}
