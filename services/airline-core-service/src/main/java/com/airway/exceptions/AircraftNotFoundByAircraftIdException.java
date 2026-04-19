package com.airway.exceptions;

public class AircraftNotFoundByAircraftIdException extends RuntimeException {
    public AircraftNotFoundByAircraftIdException(String format) {
        super(format);
    }
}
