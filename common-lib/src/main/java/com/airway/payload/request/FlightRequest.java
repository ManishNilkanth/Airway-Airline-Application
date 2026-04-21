package com.airway.payload.request;

import com.airway.enums.FlightStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightRequest {

    @NotBlank(message = "Flight number is required")
    private String flightNumber;

    private Long airlineId;

    @NotBlank(message = "Departure airport Id  is required")
    private Long departureAirportId;

    @NotBlank(message = "Arrival airport Id is required")
    private Long arrivalAirportId;

    private FlightStatus status;

}
