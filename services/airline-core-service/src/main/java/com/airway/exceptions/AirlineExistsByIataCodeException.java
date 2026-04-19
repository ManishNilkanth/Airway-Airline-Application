package com.airway.exceptions;

public class AirlineExistsByIataCodeException extends RuntimeException {
    public AirlineExistsByIataCodeException(String format) {
        super(format);
    }
}
