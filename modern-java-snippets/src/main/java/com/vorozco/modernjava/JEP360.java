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
        if (vehicle instanceof OldCar car) {
            return car.model() + " is a car with " + car.doors() + " doors";
        } else if (vehicle instanceof OldBike bike) {
            return bike.model() + " is a bike, basket: " + bike.hasBasket();
        } else {
            throw new IllegalStateException("Unknown vehicle: " + vehicle);
        }
    }
}
