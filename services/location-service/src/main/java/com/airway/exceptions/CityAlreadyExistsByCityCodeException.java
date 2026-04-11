package com.airway.exceptions;

public class CityAlreadyExistsByCityCodeException extends RuntimeException {

    public CityAlreadyExistsByCityCodeException(String message)
    {
        super(message);
    }
}
