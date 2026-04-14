package com.airway.exceptions;

public class UserExistsWithEmailException extends RuntimeException {
    public UserExistsWithEmailException(String format) {
        super(format);
    }
}
