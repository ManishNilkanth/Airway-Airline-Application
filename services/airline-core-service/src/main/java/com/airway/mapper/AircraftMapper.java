package com.airway.mapper;

import com.airway.model.Aircraft;
import com.airway.model.Airline;
import com.airway.payload.reposnse.AircraftResponse;
import com.airway.payload.request.AircraftRequest;

public class AircraftMapper {

    public static Aircraft mapToAircraft(AircraftRequest request, Airline airline)
    {
      return Aircraft.builder()
              .code(request.getCode())
              .model(request.getModel())
              .manufacturer(request.getManufacturer())
              .seatingCapacity(request.getSeatingCapacity())
              .economySeats(request.getEconomySeats())
              .premiumEconomySeats(request.getPremiumEconomySeats())
              .businessSeats(request.getBusinessSeats())
              .firstClassSeats(request.getFirstClassSeats())
              .rangeKm(request.getRangeKm())
              .cruisingSpeedKmh(request.getCruisingSpeedKmh())
              .maxAltitude(request.getMaxAltitude())
              .yearOfManufacture(request.getYearOfManufacture())
              .registrationDate(request.getRegistrationDate())
              .nextMaintenanceDate(request.getNextMaintenanceDate())
              .status(request.getStatus())
              .available(request.isAvailable())
              .airline(airline)
              .currentAirportId(request.getCurrentAirportId())
              .build();
    }

    public static AircraftResponse mapToAircraftResponse(Aircraft request)
    {
      return AircraftResponse.builder()
              .id(request.getId())
              .code(request.getCode())
              .model(request.getModel())
              .manufacturer(request.getManufacturer())
              .seatingCapacity(request.getSeatingCapacity())
              .economySeats(request.getEconomySeats())
              .premiumEconomySeats(request.getPremiumEconomySeats())
              .businessSeats(request.getBusinessSeats())
              .firstClassSeats(request.getFirstClassSeats())
              .rangeKm(request.getRangeKm())
              .cruisingSpeedKmh(request.getCruisingSpeedKmh())
              .maxAltitude(request.getMaxAltitude())
              .yearOfManufacture(request.getYearOfManufacture())
              .registrationDate(request.getRegistrationDate())
              .nextMaintenanceDate(request.getNextMaintenanceDate())
              .status(request.getStatus())
              .available(request.isAvailable())

              //Airline info
              .airlineId(request.getAirline() != null ? request.getAirline().getId() : null)
              .airlineName(request.getAirline() != null ? request.getAirline().getName() : null)
              .airlineIataCode(request.getAirline() != null ? request.getAirline().getIataCode() : null)

              //Airport is cross service - only ID available here
              .currentAirportId(request.getCurrentAirportId())

              //computed fields
              .totalSeats(request.getTotalSeats())
              .requiresMaintenance(request.isMaintenanceDueSoon())
              .isOperational(request.isOperational())

              //audit
              .createdAt(request.getCreatedAt())
              .updatedAt(request.getUpdatedAt())
              .build();
    }

    public static void updateAircraft(Aircraft aircraft, AircraftRequest request,Airline airline)
    {
        if(request == null) return;

        aircraft.setCode(request.getCode());
        aircraft.setModel(request.getModel());
        aircraft.setManufacturer(request.getManufacturer());
        aircraft.setSeatingCapacity(request.getSeatingCapacity());
        aircraft.setEconomySeats(request.getEconomySeats());
        aircraft.setPremiumEconomySeats(request.getPremiumEconomySeats());
        aircraft.setBusinessSeats(request.getBusinessSeats());
        aircraft.setFirstClassSeats(request.getFirstClassSeats());
        aircraft.setRangeKm(request.getRangeKm());
        aircraft.setCruisingSpeedKmh(request.getCruisingSpeedKmh());
        aircraft.setMaxAltitude(request.getMaxAltitude());
        aircraft.setYearOfManufacture(request.getYearOfManufacture());
        aircraft.setRegistrationDate(request.getRegistrationDate());
        aircraft.setNextMaintenanceDate(request.getNextMaintenanceDate());
        aircraft.setStatus(request.getStatus());
        aircraft.setAvailable(request.isAvailable());
        aircraft.setAirline(airline);
        aircraft.setCurrentAirportId(request.getCurrentAirportId());
    }

}
