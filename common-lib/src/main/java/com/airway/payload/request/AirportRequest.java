package com.airway.payload.request;

import com.airway.embeddable.Address;
import com.airway.embeddable.GeoCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportRequest {

    @NotBlank(message = "Airport name is required")
    private String name;

    @NotBlank(message = "Airport name is required")
    @Size(min = 3, max = 3, message = "IADA code must be exactly 3 characters")
    private String iataCode;

    private ZoneId timeZone;

    @Valid
    @NotNull(message = "Address is required")
    private Address address;

    @Valid
    @NotNull(message = "GeoCode is required")
    private GeoCode geoCode;

    @NotNull(message = "cityId is required")
    private Long cityId;

}
