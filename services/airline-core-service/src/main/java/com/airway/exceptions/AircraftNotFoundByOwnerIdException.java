package com.airway.exceptions;

public class AircraftNotFoundByOwnerIdException extends RuntimeException {
    public AircraftNotFoundByOwnerIdException(String message) {
        super(message);
    }
}
