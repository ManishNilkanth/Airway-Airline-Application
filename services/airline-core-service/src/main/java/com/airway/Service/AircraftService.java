package com.airway.Service;

import com.airway.model.Aircraft;
import com.airway.payload.reposnse.AircraftResponse;
import com.airway.payload.reposnse.ApiResponse;

import java.util.List;

public interface AircraftService {

    AircraftResponse create(Aircraft aircraft,Long ownerId);

    AircraftResponse getAircraftById(Long id);

    List<AircraftResponse> getAllAircraftByOwnerId(Long ownerId);

    AircraftResponse updateAircraft(Long ownerId, Aircraft aircraft);
    ApiResponse deleteAircraft(Long airCraftId, Long ownerId);
}
