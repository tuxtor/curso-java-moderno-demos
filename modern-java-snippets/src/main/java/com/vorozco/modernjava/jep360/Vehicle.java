package com.vorozco.modernjava.jep360;

/**
 * JEP 360: 'sealed' with a 'permits' clause tells the compiler the
 * complete, closed set of subtypes - only Car and Bike may implement
 * Vehicle. Combined with pattern matching for switch, this lets the
 * compiler verify exhaustiveness: no default branch is needed, and adding a
 * new vehicle type without updating every switch becomes a compile error
 * instead of a runtime surprise.
 */
public sealed interface Vehicle permits Car, Bike {

    String model();
}
