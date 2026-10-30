package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class JEP513Test {

  @Test
  void validBalance() {
    assertEquals("Account[balance=100]", JEP513.oldValidatedAccount(100));
    assertEquals(JEP513.oldValidatedAccount(100), JEP513.newValidatedAccount(100));
  }

  @Test
  void negativeBalanceIsRejectedByBoth() {
    var oldError = assertThrows(IllegalArgumentException.class, () -> JEP513.oldValidatedAccount(-5));
    var newError = assertThrows(IllegalArgumentException.class, () -> JEP513.newValidatedAccount(-5));
    assertEquals("balance cannot be negative", oldError.getMessage());
    assertEquals(oldError.getMessage(), newError.getMessage());
  }
}
