package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("network")
class JEP110Test {

  @Test
  void oldAndNewClientsReturnSameBody() {
    String oldBody = JEP110.oldHttpClientDemo();
    String newBody = JEP110.newHttpClientDemo();

    assertFalse(oldBody.startsWith("Error:"), oldBody);
    assertFalse(newBody.startsWith("Error:"), newBody);
    // the old client strips line breaks while reading line by line
    assertEquals(oldBody.replaceAll("\\s", ""), newBody.replaceAll("\\s", ""));
  }
}
