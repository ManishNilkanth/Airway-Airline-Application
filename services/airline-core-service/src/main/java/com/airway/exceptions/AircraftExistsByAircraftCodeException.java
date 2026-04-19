package com.airway.exceptions;

public class AircraftExistsByAircraftCodeException extends RuntimeException {
    public AircraftExistsByAircraftCodeException(String format) {
        super(format);
    }
}
