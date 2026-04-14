package com.airway.mapper;

import com.airway.model.Airline;
import com.airway.payload.reposnse.AirlineResponse;
import com.airway.payload.reposnse.AirportResponse;
import com.airway.payload.reposnse.Support;
import com.airway.payload.request.AirlineRequest;
import jakarta.validation.constraints.NotBlank;

public class AirlineMapper {

    public static Airline mapToAirline(AirlineRequest request,Long ownerId)
    {
        Airline airline=  Airline.builder()
                .iataCode(request.getIataCode())
                .icaoCode(request.getIcaoCode())
                .name(request.getName())
                .alias(request.getAlias())
                .logUrl(request.getLogUrl())
                .website(request.getWebsite())
                .alliance(request.getAlliance())
                .headquartersCityId(request.getHeadquartersCityId())
                .status(request.getStatus())
                .ownerId(ownerId)
                .build();

        if(request.getSupportEmail() != null || request.getSupportPhone() != null || request.getSupportHours() != null)
        {
            airline.setSupport(Support.builder()
                            .phone(request.getSupportPhone())
                            .email(request.getSupportEmail())
                            .hours(request.getSupportHours())
                    .build());
        }
        return airline;
    }

    public static AirlineResponse mapToAirlineResponse(Airline airline)
    {
        return AirlineResponse.builder()
                .id(airline.getId())
                .iataCode(airline.getIataCode())
                .icaoCode(airline.getIcaoCode())
                .name(airline.getName())
                .alias(airline.getAlias())
                .logUrl(airline.getLogUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .createdAt(airline.getCreatedAt())
                .updatedAt(airline.getUpdatedAt())
                .ownerId(airline.getOwnerId())
                .updatedById(airline.getUpdatedById())
                .build();
    }

    public static void updateAirline(Airline airline, AirlineRequest request)
    {
        if(request == null) return;

        if(request.getIataCode() != null)
        {
            airline.setIataCode(request.getIataCode());
        }
        if(request.getIcaoCode() != null)
        {
            airline.setIcaoCode(request.getIcaoCode());
        }
        if(request.getName() != null)
        {
            airline.setName(request.getName());
        }
        if(request.getAlias() != null)
        {
            airline.setAlias(request.getAlias());
        }
        if(request.getLogUrl() != null)
        {
            airline.setLogUrl(request.getLogUrl());
        }
        if(request.getWebsite() != null)
        {
            airline.setWebsite(request.getWebsite());
        }
        if(request.getStatus() != null)
        {
            airline.setStatus(request.getStatus());
        }
        if(request.getAlliance() != null)
        {
            airline.setAlliance(request.getAlliance());
        }
        if(request.getHeadquartersCityId() != null)
        {
            airline.setHeadquartersCityId(request.getHeadquartersCityId());
        }
        if(airline.getSupport() == null)
        {
            airline.setSupport(new Support());
        }

        if(request.getSupportEmail() != null)
        {
            airline.getSupport().setEmail(request.getSupportEmail());
        }
        if(request.getSupportPhone() != null)
        {
            airline.getSupport().setPhone(request.getSupportPhone());
        }
        if(request.getSupportHours() == null)
        {
            airline.setSupport(new Support());
        }

    }
}
