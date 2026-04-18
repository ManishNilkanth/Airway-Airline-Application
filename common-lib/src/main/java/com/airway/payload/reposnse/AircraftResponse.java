package com.airway.payload.reposnse;

import com.airway.enums.AircraftStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class AircraftResponse {

    private Long id;

    private String code;

    private String model;

    private String manufacturer;

    private Integer seatingCapacity;

    private Integer economySeats;

    private Integer premiumEconomySeats;

    private Integer businessSeats;

    private Integer firstClassSeats;

    private Integer rangeKm;

    private Integer cruisingSpeedKmh;

    private Integer maxAltitude;

    private Integer yearOfManufacture;

    private LocalDate registrationDate;

    private LocalDate nextMaintenanceDate;

    private AircraftStatus status;

    private boolean available;

    private Long airlineId;

    private String airlineName;

    private String airlineIataCode;

    private Long currentAirportId;

    private String currentAirportCity;

    private String currentAirportCode;

    private String currentAirportName;

    private Integer totalSeats;

    private Boolean requiresMaintenance;

    private Boolean isOperational;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
