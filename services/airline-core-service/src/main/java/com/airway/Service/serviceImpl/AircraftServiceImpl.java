package com.airway.Service.serviceImpl;

import com.airway.Service.AircraftService;
import com.airway.exceptions.AircraftExistsByAircraftCodeException;
import com.airway.exceptions.AircraftNotFoundByAircraftIdException;
import com.airway.exceptions.AircraftNotFoundByOwnerIdException;
import com.airway.exceptions.AirlineNotFoundByOwnerIdException;
import com.airway.mapper.AircraftMapper;
import com.airway.model.Aircraft;
import com.airway.model.Airline;
import com.airway.payload.reposnse.AircraftResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AircraftRequest;
import com.airway.repository.AircraftRepository;
import com.airway.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;

    @Override
    public AircraftResponse createAircraft(AircraftRequest request, Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->
                new AirlineNotFoundByOwnerIdException(
                     String.format("Airline not found by owner id %d", ownerId)
                )
        );

        if(aircraftRepository.existsByCode(request.getCode()))
        {
            throw new AircraftExistsByAircraftCodeException(
                    String.format("Airline exists by Aircraft Code %s",request.getCode())
            );
        }
        Aircraft aircraft = AircraftMapper.mapToAircraft(request,airline);
        Aircraft savedAircraft =  aircraftRepository.save(aircraft);

        return  AircraftMapper.mapToAircraftResponse(savedAircraft);
    }

    @Override
    public AircraftResponse getAircraftById(Long id) {

        Aircraft aircraft =  aircraftRepository.findById(id).orElseThrow(()->
                new AircraftNotFoundByAircraftIdException(
                        String.format("Aircraft not found by Aircraft id %d", id)
                )
        );
        return AircraftMapper.mapToAircraftResponse(aircraft);
    }

    @Override
    public List<AircraftResponse> getAllAircraftByOwnerId(Long ownerId) {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->
                new AirlineNotFoundByOwnerIdException(
                        String.format("Airline not found by owner id %d", ownerId)
                )
        );
        List<Aircraft> aircraftList = aircraftRepository.findByAirlineId(airline.getId());

        return aircraftList.stream()
                .map(AircraftMapper::mapToAircraftResponse)
                .toList();
    }

    @Override
    public AircraftResponse updateAircraft(Long aircraftId,Long ownerId, AircraftRequest request) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->
                new AirlineNotFoundByOwnerIdException(
                        String.format("Airline not found by owner id %d", ownerId)
                )
        );

        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(aircraftId,airline.getId());

        if(request.getCode() != null && !aircraft.getCode().equals(request.getCode()))
        {
            if(aircraftRepository.existsByCode(request.getCode()))
            {
                throw  new AircraftExistsByAircraftCodeException(
                        String.format("Airline exists by Aircraft Code %s",request.getCode())
                );
            }
        }
        AircraftMapper.updateAircraft(aircraft,request,airline);
        Aircraft savedAircraft = aircraftRepository.save(aircraft);
        return AircraftMapper.mapToAircraftResponse(savedAircraft);
    }

    @Override
    public ApiResponse deleteAircraft(Long airCraftId, Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->
                new AircraftNotFoundByOwnerIdException(
                     String.format("Airline not found by owner Id %d",ownerId)
                )
        );
        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(airCraftId,airline.getId());
        if(aircraft == null)
        {
            throw new AircraftNotFoundByAircraftIdException("Aircraft not found by aircraft Id");
        }
        aircraftRepository.delete(aircraft);

        return new ApiResponse("Aircraft has been deleted");
    }
}
