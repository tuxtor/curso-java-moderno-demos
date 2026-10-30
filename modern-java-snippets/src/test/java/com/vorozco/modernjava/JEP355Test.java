package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JEP355Test {

  @Test
  void multiLineString() {
    assertEquals("Line one\nLine two\nLine three", JEP355.oldMultiLineString());
    // a text block's closing delimiter on its own line adds a trailing newline
    assertEquals(JEP355.oldMultiLineString() + "\n", JEP355.newMultiLineString());
  }

  @Test
  void stringWithQuotes() {
    assertEquals("He said \"Hello, world!\" and left.", JEP355.oldStringWithQuotes());
    assertEquals(JEP355.oldStringWithQuotes(), JEP355.newStringWithQuotes().strip());
  }

  @Test
  void formattedString() {
    assertEquals("Hello, Copilot!\nWelcome to JEP 355.",
        JEP355.newFormattedString("Copilot"));
    // old version uses %n, which is platform-dependent
    assertEquals(JEP355.newFormattedString("Copilot"),
        JEP355.oldFormattedString("Copilot").replace("\r\n", "\n"));
  }
}
