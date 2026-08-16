package com.vorozco.modernjava.jep360;

public final class OldBike extends OldVehicle {

    private final boolean hasBasket;

    public OldBike(String model, boolean hasBasket) {
        super(model);
        this.hasBasket = hasBasket;
    }

    public boolean hasBasket() {
        return hasBasket;
    }
}
