package com.airway.payload.request;

import com.airway.enums.AircraftStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftRequest {

    @NotBlank(message = "Aircraft code is required")
    private String code;

    @NotBlank(message = "Aircraft code is model")
    private String model;

    @NotBlank(message = "Aircraft code is manufacturer")
    private String manufacturer;

    @NotNull(message = "Seating capacity is required")
    @PositiveOrZero(message = "Seating capacity must be positive or zero")
    private Integer seatingCapacity;

    @PositiveOrZero(message = "Economy seats must be positive or zero")
    private Integer economySeats;

    @PositiveOrZero(message = "Premium economy seats must be positive or zero")
    private Integer premiumEconomySeats;

    @PositiveOrZero(message = "Business seats must be positive or zero")
    private Integer businessSeats;

    @PositiveOrZero(message = "First class seats must be positive or zero")
    private Integer firstClassSeats;

    @PositiveOrZero(message = "Range must be positive or zero")
    private Integer rangeKm;

    @PositiveOrZero(message = "Cruising speed must be positive or zero")
    private Integer cruisingSpeedKmh;

    @PositiveOrZero(message = "Maximum altitude must be positive or zero")
    private Integer maxAltitude;

    @Positive(message = "Year of manufacture must be positive")
    private Integer yearOfManufacture;

    private LocalDate registrationDate;

    private LocalDate nextMaintenanceDate;

    @NotNull(message = "Aircraft status is required")
    private AircraftStatus status = AircraftStatus.ACTIVE;

    @NotNull(message = "Aircraft availability status is required")
    private boolean available;

    private Long currentAirportId;

}
