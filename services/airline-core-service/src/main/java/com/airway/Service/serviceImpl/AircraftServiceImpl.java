package com.airway.Service.serviceImpl;

import com.airway.Service.AircraftService;
import com.airway.exceptions.AircraftNotFoundByOwnerId;
import com.airway.model.Aircraft;
import com.airway.payload.reposnse.AircraftResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.repository.AircraftRepository;
import com.airway.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    @Override
    public AircraftResponse create(Aircraft aircraft, Long ownerId) {

        if(!aircraftRepository.existsByOwnerId(ownerId)){
            throw new AircraftNotFoundByOwnerId(
                    String.format("Aircraft not found with owner ID %d", ownerId)
            );
        }

        if(aircraftRepository.existsByCode(aircraft.getCode())){}
        return null;
    }

    @Override
    public AircraftResponse getAircraftById(Long id) {
        return null;
    }

    @Override
    public List<AircraftResponse> getAllAircraftByOwnerId(Long ownerId) {
        return List.of();
    }

    @Override
    public AircraftResponse updateAircraft(Long ownerId, Aircraft aircraft) {
        return null;
    }

    @Override
    public ApiResponse deleteAircraft(Long airCraftId, Long ownerId) {
        return null;
    }
}
