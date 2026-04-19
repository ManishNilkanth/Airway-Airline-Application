package com.airway.Service;

import com.airway.payload.reposnse.AircraftResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AircraftRequest;

import java.util.List;

public interface AircraftService {

    AircraftResponse createAircraft(AircraftRequest request, Long ownerId);

    AircraftResponse getAircraftById(Long id);

    List<AircraftResponse> getAllAircraftByOwnerId(Long ownerId);

    AircraftResponse updateAircraft(Long aircraftId,Long ownerId, AircraftRequest request);
    ApiResponse deleteAircraft(Long airCraftId, Long ownerId);
}
