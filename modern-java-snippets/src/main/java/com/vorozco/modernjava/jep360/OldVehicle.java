package com.vorozco.modernjava.jep360;

public sealed interface OldVehicle permits OldCar, OldBike {

  String model();

}
