package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JEP406Test {

  @Test
  void integer() {
    assertEquals("an Integer: 42", JEP406.oldDescribe(42));
    assertEquals(JEP406.oldDescribe(42), JEP406.newDescribe(42));
  }

  @Test
  void longString() {
    assertEquals("a long String: Hello, JEP 406!", JEP406.oldDescribe("Hello, JEP 406!"));
    assertEquals("an long String: Hello, JEP 406!", JEP406.newDescribe("Hello, JEP 406!"));
  }

  @Test
  void shortString() {
    assertEquals("a short String: Hi", JEP406.oldDescribe("Hi"));
    assertEquals("an String: Hi", JEP406.newDescribe("Hi"));
  }

  @Test
  void nullValue() {
    assertEquals("it's null", JEP406.oldDescribe(null));
    assertEquals("it is a null", JEP406.newDescribe(null));
  }

  @Test
  void otherTypes() {
    assertEquals("something else", JEP406.oldDescribe(3.14));
    assertEquals(JEP406.oldDescribe(3.14), JEP406.newDescribe(3.14));
  }
}
