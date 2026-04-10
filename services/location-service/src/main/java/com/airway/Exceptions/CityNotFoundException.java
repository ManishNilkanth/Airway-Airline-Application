package com.airway.Exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String format) {
        super(format);
    }
}
