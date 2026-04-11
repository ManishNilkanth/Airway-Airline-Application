package com.airway.Service;

import com.airway.payload.reposnse.AirportResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AirportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirportService {

    AirportResponse createAirpost(AirportRequest request);

    AirportResponse getAirportById(Long id);

    Page<AirportResponse> getAllAirports(Pageable pageable);

    List<AirportResponse> getAllAirportByCityId(Long cityId);

    AirportResponse updateAirport(Long id, AirportRequest request);

    ApiResponse deleteAirport(Long id);
}
