package com.airway.Service;

import com.airway.enums.AirlineStatus;
import com.airway.payload.reposnse.AirlineDropdownItem;
import com.airway.payload.reposnse.AirlineResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AirlineRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest request, Long ownerId);

    AirlineResponse getAirlineById(Long airlineId);

    Page<AirlineResponse> getAllAirlines(Pageable pageable);

    Page<AirlineResponse> getAllAirlinesByOwnerId(Pageable pageable, Long ownerId);

    AirlineResponse updateAirline(AirlineRequest request,Long ownerId,Long airlineId);

    ApiResponse deleteAirLine(Long id, Long ownerId);

    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status);

    List<AirlineDropdownItem> getAirlineDropdown();
}
