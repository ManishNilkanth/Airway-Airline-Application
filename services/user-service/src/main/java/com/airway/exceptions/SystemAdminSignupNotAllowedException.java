package com.airway.exceptions;

public class SystemAdminSignupNotAllowedException extends RuntimeException {
    public SystemAdminSignupNotAllowedException(String message) {
        super(message);
    }
}
