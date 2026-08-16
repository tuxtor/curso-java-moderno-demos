package com.vorozco.modernjava.jep360;

public final class OldCar extends OldVehicle {

    private final int doors;

    public OldCar(String model, int doors) {
        super(model);
        this.doors = doors;
    }

    public int doors() {
        return doors;
    }
}
