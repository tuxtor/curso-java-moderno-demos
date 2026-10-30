package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JEP286Test {

  @Test
  void primitiveDeclaration() {
    assertEquals("count = 42", JEP286.oldPrimitiveDeclaration());
    assertEquals(JEP286.oldPrimitiveDeclaration(), JEP286.newPrimitiveDeclaration());
  }

  @Test
  void objectDeclaration() {
    assertEquals("Hello, JEP 286!", JEP286.oldObjectDeclaration());
    assertEquals(JEP286.oldObjectDeclaration(), JEP286.newObjectDeclaration());
  }

  @Test
  void forEachLoop() {
    assertEquals("Java Kotlin Scala", JEP286.oldForEachLoop());
    assertEquals(JEP286.oldForEachLoop(), JEP286.newForEachLoop());
  }

  @Test
  void tryWithResources() {
    assertEquals("First char: d", JEP286.oldTryWithResources());
    assertEquals(JEP286.oldTryWithResources(), JEP286.newTryWithResources());
  }
}
