package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class JEP305Test {

  static Stream<Object> inputs() {
    return Stream.of("Hello, JEP 305!", "Hi", 42, 3.14, new Object());
  }

  @ParameterizedTest
  @MethodSource("inputs")
  void instanceofWithCast(Object input) {
    assertEquals(Demos.unlabel(JEP305.oldInstanceofWithCast(input)),
        Demos.unlabel(JEP305.newInstanceofWithCast(input)));
  }

  @ParameterizedTest
  @MethodSource("inputs")
  void combinedCondition(Object input) {
    assertEquals(Demos.unlabel(JEP305.oldCombinedCondition(input)),
        Demos.unlabel(JEP305.newCombinedCondition(input)));
  }

  @org.junit.jupiter.api.Test
  void concreteResults() {
    assertEquals("(new) String of length 2", JEP305.newInstanceofWithCast("Hi"));
    assertEquals("(new) not a String", JEP305.newInstanceofWithCast(42));
    assertEquals("(new) long string: Hello, JEP 305!", JEP305.newCombinedCondition("Hello, JEP 305!"));
    assertEquals("(new) short or not a string", JEP305.newCombinedCondition("Hi"));
  }
}
