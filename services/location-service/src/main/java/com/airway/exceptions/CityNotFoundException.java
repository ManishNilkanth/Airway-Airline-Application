package com.airway.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String format) {
        super(format);
    }
}
