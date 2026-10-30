package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class JEP456Test {

  @Test
  void unnamedVariableVisitsSameKeys() {
    String oldOutput = Demos.captureStdout(JEP456::oldForeachLoop);
    String newOutput = Demos.captureStdout(JEP456::newForeachLoop);

    assertEquals(List.of("Java", "Kotlin", "Scala"), Demos.sortedElements(oldOutput));
    assertEquals(Demos.sortedElements(oldOutput), Demos.sortedElements(newOutput));
  }
}
