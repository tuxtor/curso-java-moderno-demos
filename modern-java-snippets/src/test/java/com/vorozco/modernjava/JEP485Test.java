package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class JEP485Test {

  static Stream<List<Integer>> inputs() {
    return Stream.of(
        List.of(),
        List.of(1),
        List.of(1, 2, 3),
        List.of(1, 2, 3, 4, 5, 6, 7));
  }

  @ParameterizedTest
  @MethodSource("inputs")
  void batchesMatch(List<Integer> numbers) {
    assertEquals(JEP485.oldBatchesOfThree(numbers), JEP485.newBatchesOfThree(numbers));
  }

  @org.junit.jupiter.api.Test
  void lastBatchMayBeShorter() {
    assertEquals(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7)),
        JEP485.newBatchesOfThree(List.of(1, 2, 3, 4, 5, 6, 7)));
  }
}
