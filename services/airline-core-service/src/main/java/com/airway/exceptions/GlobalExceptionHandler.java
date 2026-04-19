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

    @ExceptionHandler(AircraftExistsByAircraftCodeException.class)
    public ResponseEntity<String> AircraftExistsByAircraftCodeHandler(AircraftExistsByAircraftCodeException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(AirlineExistsByIataCodeException.class)
    public ResponseEntity<String> AirlineExistsByIataCodeHandler(AirlineExistsByIataCodeException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(AirlineExistsByIcaoCodeException.class)
    public ResponseEntity<String> AirlineExistsByIcaoCodeHandler(AirlineExistsByIcaoCodeException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @ExceptionHandler(AircraftNotFoundByAircraftIdException.class)
    public ResponseEntity<String> AircraftNotFoundByAircraftIdHandler(AircraftNotFoundByAircraftIdException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AircraftNotFoundByOwnerIdException.class)
    public ResponseEntity<String> AircraftNotFoundByOwnerIdHandler(AircraftNotFoundByOwnerIdException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AirlineNotFoundByAirlineIdException.class)
    public ResponseEntity<String> AirlineNotFoundByAirlineIdHandler(AirlineNotFoundByAirlineIdException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AirlineNotFoundByOwnerIdException.class)
    public ResponseEntity<String> AirlineNotFoundByOwnerIdHandler(AirlineNotFoundByOwnerIdException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(SeatCountMissMatchException.class)
    public ResponseEntity<String> SeatCountMissMatchExceptionHandler(SeatCountMissMatchException ex)
    {
        log.warn("Business Exception: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
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
