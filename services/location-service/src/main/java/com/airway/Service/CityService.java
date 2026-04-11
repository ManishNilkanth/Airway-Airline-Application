package com.airway.Service;

import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.reposnse.CityResponse;
import com.airway.payload.request.CityRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    CityResponse createCity(CityRequest request);

    CityResponse getCityById(Long id);

    CityResponse updateCity(Long id, CityRequest request);

    ApiResponse deleteCity(Long id);

    Page<CityResponse> getAllCities(Pageable pageable);

    Page<CityResponse> searchCities(String keyword, Pageable pageable);

    Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable);

    boolean cityExists(String cityCode);
}
