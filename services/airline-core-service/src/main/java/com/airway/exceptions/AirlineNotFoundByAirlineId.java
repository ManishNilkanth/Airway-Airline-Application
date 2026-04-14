package com.airway.exceptions;

public class AirlineNotFoundByAirlineId extends RuntimeException{
    public AirlineNotFoundByAirlineId(String format) {
        super(format);
    }
}
