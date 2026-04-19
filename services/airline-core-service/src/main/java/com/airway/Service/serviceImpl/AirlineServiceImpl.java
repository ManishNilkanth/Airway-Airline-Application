package com.airway.Service.serviceImpl;

import com.airway.Service.AirlineService;
import com.airway.enums.AirlineStatus;
import com.airway.exceptions.*;
import com.airway.mapper.AirlineMapper;
import com.airway.model.Airline;
import com.airway.payload.reposnse.AirlineDropdownItem;
import com.airway.payload.reposnse.AirlineResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AirlineRequest;
import com.airway.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public AirlineResponse createAirline(AirlineRequest request, Long ownerId) {

        if(airlineRepository.existsByIataCode(request.getIataCode()))
        {
            throw new AirlineExistsByIataCodeException(
                    String.format("Airline exists by Iata Code %s",request.getIataCode())
            );
        }
        if(airlineRepository.existsByIcaoCode(request.getIcaoCode()))
        {
            throw new AirlineExistsByIcaoCodeException(
                    String.format("Airline exists by Icao Code %s",request.getIcaoCode())
            );
        }
        Airline airline = AirlineMapper.mapToAirline(request,ownerId);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.mapToAirlineResponse(savedAirline);
    }

    @Override
    public AirlineResponse getAirlineById(Long id) {
        Airline airline = airlineRepository.findById(id).orElseThrow(()->
                new AirlineNotFoundByAirlineIdException(
                        String.format("Airline not found with airline Id %d",id)
                )
        );
        return AirlineMapper.mapToAirlineResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        Page<Airline> airlines = airlineRepository.findAll(pageable);

        if(airlines == null)
        {
            throw new AirlineNotFoundByAirlineIdException("Airline not found");
        }
        return airlines.map(AirlineMapper::mapToAirlineResponse);
    }

    @Override
    public Page<AirlineResponse> getAllAirlinesByOwnerId(Pageable pageable, Long ownerId) {
        Page<Airline> airlines = airlineRepository.findAllByOwnerId(pageable,ownerId);
        if(airlines == null)
        {
            throw new AirlineNotFoundByAirlineIdException("Airline not found");
        }
        return airlines.map(AirlineMapper::mapToAirlineResponse);
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest request, Long ownerId,Long airlineId) {

        if(!airlineRepository.existsByOwnerId(ownerId))
        {
            throw new AircraftNotFoundByOwnerIdException(
                    String.format("Airline not found by owner id %d",ownerId)
            );
        }

        Airline airline = airlineRepository.findById(airlineId).orElseThrow(()->
                new AirlineNotFoundByAirlineIdException(
                   String.format("Airline not found with airline id %d",airlineId)
                )
        );

        if(!airline.getIataCode().equals(request.getIataCode()) &&
                airlineRepository.existsByIataCode(request.getIataCode()))
        {
            throw new AirlineExistsByIataCodeException(
                    String.format("Airline exists by Iata Code %s",request.getIataCode())
            );
        }
        if(!airline.getIcaoCode().equals(request.getIcaoCode()) &&
                airlineRepository.existsByIcaoCode(request.getIcaoCode()))
        {
            throw new AirlineExistsByIcaoCodeException(
                    String.format("Airline exists by Icao Code %s",request.getIcaoCode())
            );
        }
        AirlineMapper.updateAirline(airline,request);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.mapToAirlineResponse(savedAirline);
    }

    @Override
    public ApiResponse deleteAirLine(Long id, Long ownerId) {
        if(!airlineRepository.existsByOwnerId(ownerId))
        {
            throw new AirlineNotFoundByOwnerIdException(
                    String.format("Airline not found with owner Id %d",ownerId)
            );
        }
        if(!airlineRepository.existsById(id))
        {
            throw new AirlineNotFoundByAirlineIdException(
                    String.format("Airline not found with airline Id %d",id)
            );
        }

        airlineRepository.deleteById(id);
        return new ApiResponse("Airline has been deleted");
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(()->
                new AirlineNotFoundByAirlineIdException(
                        String.format("Airline not found with airline Id %d",airlineId)
                )
        );
        airline.setStatus(status);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.mapToAirlineResponse(savedAirline);
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown() {
        List<Airline> airlines = airlineRepository.findByStatus(AirlineStatus.ACTIVE);
        return airlines
                .stream()
                .map(airline -> AirlineDropdownItem.builder()
                        .id(airline.getId())
                        .name(airline.getName())
                        .iadaCode(airline.getIataCode())
                        .icaoCode(airline.getIcaoCode())
                        .logoUrl(airline.getLogUrl())
                        .country("India")  //todo need to change dynamically
                        .build())
                .toList();
    }
}
