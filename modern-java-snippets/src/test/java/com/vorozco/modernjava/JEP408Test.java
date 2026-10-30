package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class JEP408Test {

  @Test
  void bothServersStartOnAnEphemeralPort() throws IOException {
    assertTrue(JEP408.oldFileServer().matches("\\(old\\) server started on port \\d+"));
    assertTrue(JEP408.newFileServer().matches("\\(new\\) server started on port \\d+"));
  }
}
