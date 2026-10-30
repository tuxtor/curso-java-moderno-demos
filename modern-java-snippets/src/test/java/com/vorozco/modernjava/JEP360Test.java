package com.vorozco.modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vorozco.modernjava.jep360.OldBike;
import com.vorozco.modernjava.jep360.OldCar;
import org.junit.jupiter.api.Test;

class JEP360Test {

  @Test
  void describesEveryPermittedSubtype() {
    assertEquals("Corolla is a car with 4 doors", JEP360.oldDescribe(new OldCar("Corolla", 4)));
    assertEquals("Mountain Bike is a bike, basket: true",
        JEP360.oldDescribe(new OldBike("Mountain Bike", true)));
  }
}
