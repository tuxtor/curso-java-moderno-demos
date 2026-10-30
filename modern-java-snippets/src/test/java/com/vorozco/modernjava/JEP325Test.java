package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class JEP325Test {

  @ParameterizedTest
  @CsvSource({
      "MONDAY,6", "TUESDAY,7", "WEDNESDAY,9", "THURSDAY,8",
      "FRIDAY,6", "SATURDAY,8", "SUNDAY,6", "NOTADAY,0"
  })
  void switchStatement(String day, int expected) {
    assertEquals(day + " (old) -> " + expected, JEP325.oldSwitchStatement(day));
    assertEquals(day + " (new) -> " + expected, JEP325.newSwitchStatement(day));
  }

  @ParameterizedTest
  @ValueSource(strings = { "TUESDAY", "MONDAY", "" })
  void switchWithBlockBody(String day) {
    assertEquals(Demos.unlabel(JEP325.oldSwitchWithBlockBody(day)),
        Demos.unlabel(JEP325.newSwitchWithBlockBody(day)));
  }
}
