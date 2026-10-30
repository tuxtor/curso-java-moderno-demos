package com.vorozco.modernjava;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

final class Demos {

  private Demos() {
  }

  /** Removes the "(old) "/"(new) " labels the demos add to their output. */
  static String unlabel(String s) {
    return s.replace("(old) ", "").replace("(new) ", "");
  }

  /** Splits a collection's toString() into sorted elements, ignoring iteration order. */
  static List<String> sortedElements(String collectionToString) {
    return Arrays.stream(collectionToString.replaceAll("[\\[\\]{}]", "").split(",\\s*"))
        .sorted()
        .toList();
  }

  static String captureStdout(Runnable action) {
    PrintStream original = System.out;
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    System.setOut(new PrintStream(buffer, true));
    try {
      action.run();
    } finally {
      System.setOut(original);
    }
    return buffer.toString().strip();
  }
}
