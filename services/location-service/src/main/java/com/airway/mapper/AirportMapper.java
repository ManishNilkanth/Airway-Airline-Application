package com.airway.mapper;

import com.airway.model.Airport;
import com.airway.model.City;
import com.airway.payload.reposnse.AirportResponse;
import com.airway.payload.request.AirportRequest;

public class AirportMapper {

    public static Airport maptoAirport(AirportRequest request)
    {
        if(request == null) return  null;

        return Airport.builder()
                .name(request.getName())
                .iataCode(request.getIataCode())
                .geoCode(request.getGeoCode())
                .address(request.getAddress())
//                .timeZone(request.getTimeZone())
                .build();
    }

    public static AirportResponse mapToAirportResponse(Airport airport)
    {
        if(airport == null) return null;

        return AirportResponse.builder()
                .id(airport.getId())
                .name(airport.getName())
                .iataCode(airport.getIataCode())
//                .timeZone(airport.getTimeZone())
                .address(airport.getAddress())
                .geoCode(airport.getGeoCode())
                .city(CityMapper.mapToCityResponse(airport.getCity()))
                .build();
    }

    public static Airport uptadeAirport(Airport airport,AirportRequest request)
    {
        if(request == null) return  airport;

        if(request.getName() != null)  airport.setName(request.getName());

        if(request.getIataCode() != null) airport.setIataCode(request.getIataCode());

        if(request.getAddress() != null) airport.setAddress(request.getAddress());

        if(request.getGeoCode() != null) airport.setGeoCode(request.getGeoCode());

        return airport;
    }
}
