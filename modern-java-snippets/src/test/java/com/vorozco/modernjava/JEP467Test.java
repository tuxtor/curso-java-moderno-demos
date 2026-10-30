package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class JEP467Test {

  @ParameterizedTest
  @CsvSource({ "0,1", "1,1", "5,120", "10,3628800" })
  void factorialIsEquivalent(int n, long expected) {
    assertEquals(expected, JEP467.oldDocumentedFactorial(n));
    assertEquals(expected, JEP467.newDocumentedFactorial(n));
  }
}
