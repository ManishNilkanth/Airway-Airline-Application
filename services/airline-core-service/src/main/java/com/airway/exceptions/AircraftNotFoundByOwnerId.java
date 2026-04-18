package com.airway.exceptions;

public class AircraftNotFoundByOwnerId extends RuntimeException {
    public AircraftNotFoundByOwnerId(String message) {
        super(message);
    }
}
