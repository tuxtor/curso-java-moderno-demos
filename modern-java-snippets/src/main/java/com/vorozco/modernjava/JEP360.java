package com.vorozco.modernjava;

import com.vorozco.modernjava.jep360.OldBike;
import com.vorozco.modernjava.jep360.OldCar;
import com.vorozco.modernjava.jep360.OldVehicle;

public class JEP360 {

  public static void main(String[] args) {
    OldVehicle oldCar = new OldCar("Corolla", 4);
    OldVehicle oldBike = new OldBike("Mountain Bike", true);
    System.out.println("(old) " + oldDescribe(oldCar));
    System.out.println("(old) " + oldDescribe(oldBike));
  }

  private static String oldDescribe(OldVehicle vehicle) {

    return switch (vehicle) {
      case OldCar car -> car.model() + " is a car with " + car.doors() + " doors";
      case OldBike bike -> bike.model() + " is a bike, basket: " + bike.hasBasket();
    };

  }
}
