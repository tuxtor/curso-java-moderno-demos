package com.vorozco.modernjava.jep360;

public abstract class OldVehicle {

    private final String model;

    protected OldVehicle(String model) {
        this.model = model;
    }

    public String model() {
        return model;
    }
}
