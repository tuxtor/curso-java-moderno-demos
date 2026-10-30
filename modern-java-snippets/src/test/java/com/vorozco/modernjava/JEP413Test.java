package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JEP413Test {

  @Test
  void documentedAddIsEquivalent() {
    assertEquals(5, JEP413.oldDocumentedAdd(2, 3));
    assertEquals(JEP413.oldDocumentedAdd(2, 3), JEP413.newDocumentedAdd(2, 3));
  }
}
