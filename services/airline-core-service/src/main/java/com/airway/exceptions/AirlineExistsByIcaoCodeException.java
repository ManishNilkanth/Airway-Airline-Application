package com.airway.exceptions;

public class AirlineExistsByIcaoCodeException extends RuntimeException {
    public AirlineExistsByIcaoCodeException(String format) {
        super(format);
    }
}
