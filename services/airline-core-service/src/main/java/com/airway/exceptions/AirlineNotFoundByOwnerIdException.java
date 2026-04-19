package com.airway.exceptions;

public class AirlineNotFoundByOwnerIdException extends RuntimeException{
    public AirlineNotFoundByOwnerIdException(String message) {
        super(message);
    }
}
