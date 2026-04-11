package com.airway.controller;

import com.airway.Service.CityService;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.reposnse.CityResponse;
import com.airway.payload.request.CityRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest request)
    {
        CityResponse response = cityService.createCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@Valid @RequestBody CityRequest request, @PathVariable Long id)
    {
        CityResponse response = cityService.updateCity(id,request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id)
    {
        CityResponse response = cityService.getCityById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<Page<CityResponse>> getAllCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    )
    {
         Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
         Pageable pageable = PageRequest.of(page,size,sort);
         Page<CityResponse> responses = cityService.getAllCities(pageable);

         return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchByKeyword(
            @RequestParam() String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    )
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<CityResponse> responses = cityService.searchCities(keyword,pageable);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/country/{countryCode}")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountryCode(
            @PathVariable String countryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    )
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<CityResponse> responses = cityService.getCitiesByCountryCode(countryCode,pageable);

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCityById(@PathVariable Long id)
    {
        ApiResponse  apiResponse = cityService.deleteCity(id);
        return ResponseEntity.ok(apiResponse);
    }

}
