package com.airway.payload.reposnse;

import com.airway.embeddable.Address;
import com.airway.embeddable.GeoCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportResponse {

    private Long id;

    private String name;

    private String iataCode;

    private String detailedName;

    private ZoneId timeZone;

    private Address address;

    private CityResponse city;

    private GeoCode geoCode;
}
