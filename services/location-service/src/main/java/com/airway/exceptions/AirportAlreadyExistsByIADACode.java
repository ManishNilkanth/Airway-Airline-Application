package com.airway.exceptions;

public class AirportAlreadyExistsByIADACode extends RuntimeException {
    public AirportAlreadyExistsByIADACode(String message) {
        super(message);
    }
}
