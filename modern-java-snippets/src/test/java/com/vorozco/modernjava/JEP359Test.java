package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JEP359Test {

  @Test
  void onlyTheRecordGetsEqualsAndHashCodeForFree() {
    // OldPoint doesn't declare equals/hashCode, so it falls back to identity semantics
    assertEquals("(old) equals: false, hashCode match: false", JEP359.oldEqualsComparison());
    assertEquals("(new) equals: true, hashCode match: true", JEP359.newEqualsComparison());
  }

  @Test
  void toStringUsesSameShape() {
    assertEquals("OldPoint[x=3, y=4]", JEP359.oldVerboseClass().replace("(old) ", ""));
    assertEquals("NewPoint[x=3, y=4]", JEP359.newVerboseClass().replace("(new) ", ""));
  }
}
