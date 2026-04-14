package com.airway.exceptions;

public class AirlineNotFoundByOwnerId extends RuntimeException{
    public AirlineNotFoundByOwnerId(String message) {
        super(message);
    }
}
