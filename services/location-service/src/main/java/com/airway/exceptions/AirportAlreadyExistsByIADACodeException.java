package com.airway.exceptions;

public class AirportAlreadyExistsByIADACodeException extends RuntimeException {
    public AirportAlreadyExistsByIADACodeException(String message) {
        super(message);
    }
}
