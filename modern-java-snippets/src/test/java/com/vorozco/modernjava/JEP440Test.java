package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JEP440Test {

  @Test
  void pointDescribe() {
    Object point = new JEP440.Point(3, 4);
    assertEquals("Point(3, 4)", Demos.unlabel(JEP440.oldPointDescribe(point)));
    assertEquals(Demos.unlabel(JEP440.oldPointDescribe(point)),
        Demos.unlabel(JEP440.newPointDescribe(point)));
  }

  @Test
  void pointDescribeRejectsOtherTypes() {
    assertEquals("not a Point", Demos.unlabel(JEP440.oldPointDescribe("x")));
    assertEquals("not a Point", Demos.unlabel(JEP440.newPointDescribe("x")));
  }

  @Test
  void lineDescribe() {
    Object line = new JEP440.Line(new JEP440.Point(0, 0), new JEP440.Point(3, 4));
    assertEquals("Line from (0,0) to (3,4)", Demos.unlabel(JEP440.oldLineDescribe(line)));
    assertEquals(Demos.unlabel(JEP440.oldLineDescribe(line)),
        Demos.unlabel(JEP440.newLineDescribe(line)));
  }

  @Test
  void lineDescribeRejectsOtherTypes() {
    assertEquals("not a Line", Demos.unlabel(JEP440.oldLineDescribe(42)));
    assertEquals("not a Line", Demos.unlabel(JEP440.newLineDescribe(42)));
  }
}
