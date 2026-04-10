package com.airway.Exceptions;

public class CityAlreadyExistsByCityCodeException extends RuntimeException {

    public CityAlreadyExistsByCityCodeException(String message)
    {
        super(message);
    }
}
