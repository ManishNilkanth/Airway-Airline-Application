package com.airway.Service.serviceImpl;

import com.airway.Service.AirlineService;
import com.airway.enums.AirlineStatus;
import com.airway.exceptions.AirlineNotFoundByAirlineId;
import com.airway.exceptions.AirlineNotFoundByOwnerId;
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

        Airline airline = AirlineMapper.mapToAirline(request,ownerId);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.mapToAirlineResponse(savedAirline);
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->
                new AirlineNotFoundByOwnerId(
                        String.format("Airline not found with owner Id %d",ownerId)
                )
        );
        return AirlineMapper.mapToAirlineResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineById(Long id) {
        Airline airline = airlineRepository.findById(id).orElseThrow(()->
                new AirlineNotFoundByAirlineId(
                        String.format("Airline not found with airline Id %d",id)
                )
        );
        return AirlineMapper.mapToAirlineResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        return airlineRepository.findAll(pageable)
                .map(AirlineMapper::mapToAirlineResponse);
    }

    @Override
    public Page<AirlineResponse> getAllAirlinesByOwner(Pageable pageable, Long ownerId) {
        return airlineRepository.findAllByOwnerId(pageable,ownerId)
                .map(AirlineMapper::mapToAirlineResponse);
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest request, Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->
                new AirlineNotFoundByOwnerId(
                        String.format("Airline not found with owner Id %d",ownerId)
                )
        );
        AirlineMapper.updateAirline(airline,request);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.mapToAirlineResponse(savedAirline);
    }

    @Override
    public ApiResponse deleteAirLine(Long id, Long ownerId) {
        if(!airlineRepository.existsByOwnerId(ownerId))
        {
            throw new AirlineNotFoundByOwnerId(
                    String.format("Airline not found with owner Id %d",ownerId)
            );
        }
        if(!airlineRepository.existsById(id))
        {
            throw new AirlineNotFoundByAirlineId(
                    String.format("Airline not found with airline Id %d",id)
            );
        }

        airlineRepository.deleteById(id);
        return new ApiResponse("Airline has been deleted");
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(()->
                new AirlineNotFoundByAirlineId(
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
                        .build())
                .toList();
    }
}
