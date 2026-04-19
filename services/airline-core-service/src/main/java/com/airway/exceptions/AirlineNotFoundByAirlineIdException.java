package com.airway.exceptions;

public class AirlineNotFoundByAirlineIdException extends RuntimeException{
    public AirlineNotFoundByAirlineIdException(String format) {
        super(format);
    }
}
