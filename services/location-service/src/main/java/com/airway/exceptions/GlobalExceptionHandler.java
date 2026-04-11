package com.airway.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericExceptionHandler(Exception ex)
    {
        log.error("Unexpected system error :",ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> CityNotFoundExceptionHandler(CityNotFoundException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CityAlreadyExistsByCityCodeException.class)
    public ResponseEntity<String> CityAlreadyExistsByCityCodeExceptionHandler(CityAlreadyExistsByCityCodeException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(AirportAlreadyExistsByIADACode.class)
    public ResponseEntity<String> AirportAlreadyExistsByIADACodeExceptionHandler(AirportAlreadyExistsByIADACode ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<String> AirportNotFoundExceptionHandler(AirportNotFoundException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });

        ValidationErrorResponse response = new ValidationErrorResponse(
                java.time.LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
