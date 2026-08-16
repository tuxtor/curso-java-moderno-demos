package com.vorozco.modernjava;

import com.vorozco.modernjava.jep360.Bike;
import com.vorozco.modernjava.jep360.Car;
import com.vorozco.modernjava.jep360.OldBike;
import com.vorozco.modernjava.jep360.OldCar;
import com.vorozco.modernjava.jep360.OldVehicle;
import com.vorozco.modernjava.jep360.Vehicle;

public class JEP360 {

    public static void main(String[] args) {
        OldVehicle oldCar = new OldCar("Corolla", 4);
        OldVehicle oldBike = new OldBike("Mountain Bike", true);
        System.out.println("(old) " + oldDescribe(oldCar));
        System.out.println("(old) " + oldDescribe(oldBike));

        Vehicle newCar = new Car("Corolla", 4);
        Vehicle newBike = new Bike("Mountain Bike", true);
        System.out.println("(new) " + newDescribe(newCar));
        System.out.println("(new) " + newDescribe(newBike));
    }

    /**
     * OldVehicle is not sealed, so the compiler can never be sure it has
     * seen every subclass - callers are forced to fall back to a catch-all
     * branch even when they believe they've handled every known case.
     */
    private static String oldDescribe(OldVehicle vehicle) {
        if (vehicle instanceof OldCar car) {
            return car.model() + " is a car with " + car.doors() + " doors";
        } else if (vehicle instanceof OldBike bike) {
            return bike.model() + " is a bike, basket: " + bike.hasBasket();
        } else {
            // Unreachable today, but the compiler can't guarantee that -
            // any code, anywhere, could add a new OldVehicle subclass.
            throw new IllegalStateException("Unknown vehicle: " + vehicle);
        }
    }

    /**
     * Vehicle is sealed and permits only Car and Bike, so this switch is
     * exhaustive - no default branch is needed, and the compiler would
     * reject this method if a new permitted subtype were added without
     * handling it here.
     */
    private static String newDescribe(Vehicle vehicle) {
        return switch (vehicle) {
            case Car car -> car.model() + " is a car with " + car.doors() + " doors";
            case Bike bike -> bike.model() + " is a bike, basket: " + bike.hasBasket();
        };
    }
}
