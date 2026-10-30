package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JEP323Test {

  @Test
  void singleParam() {
    assertEquals("HELLO JEP 323", JEP323.oldSingleParamLambda());
    assertEquals(JEP323.oldSingleParamLambda(), JEP323.newSingleParamLambda());
  }

  @Test
  void multiParam() {
    assertEquals("sum = 7", JEP323.oldMultiParamLambda());
    assertEquals(JEP323.oldMultiParamLambda(), JEP323.newMultiParamLambda());
  }

  @Test
  void annotatedParam() {
    assertEquals("[padded]", JEP323.oldAnnotatedParamLambda());
    assertEquals(JEP323.oldAnnotatedParamLambda(), JEP323.newAnnotatedParamLambda());
  }
}
