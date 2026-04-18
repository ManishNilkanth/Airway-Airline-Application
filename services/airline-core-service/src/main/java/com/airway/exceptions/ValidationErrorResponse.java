package com.airway.exceptions;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationErrorResponse {

    private String timestamp;
    private int status;
    private Map<String, String> errors;

    public ValidationErrorResponse(String timestamp, int status, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
    }

}