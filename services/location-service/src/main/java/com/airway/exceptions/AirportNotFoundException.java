package com.airway.exceptions;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(String format) {
        super(format);
    }
}
