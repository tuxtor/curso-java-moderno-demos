package com.vorozco.modernjava.jep360;

/**
 * Before JEP 360, a class could not restrict who is allowed to extend it
 * (short of making it final, which blocks all subclassing). Anyone, in any
 * package, could add a new subclass of OldVehicle without this class ever
 * knowing about it.
 */
public abstract class OldVehicle {

    private final String model;

    protected OldVehicle(String model) {
        this.model = model;
    }

    public String model() {
        return model;
    }
}
