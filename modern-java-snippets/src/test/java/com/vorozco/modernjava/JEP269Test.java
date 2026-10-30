package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class JEP269Test {

  @Test
  void listsAreIdentical() {
    assertEquals("[Java, Python, JavaScript]", JEP269.oldListCreation());
    assertEquals(JEP269.oldListCreation(), JEP269.newListCreation());
  }

  @Test
  void setsHaveSameElements() {
    assertEquals(List.of("Blue", "Green", "Red"), Demos.sortedElements(JEP269.oldSetCreation()));
    assertEquals(Demos.sortedElements(JEP269.oldSetCreation()),
        Demos.sortedElements(JEP269.newSetCreation()));
  }

  @Test
  void mapsHaveSameEntries() {
    assertEquals(List.of("one=1", "three=3", "two=2"), Demos.sortedElements(JEP269.oldMapCreation()));
    assertEquals(Demos.sortedElements(JEP269.oldMapCreation()),
        Demos.sortedElements(JEP269.newMapCreation()));
  }
}
