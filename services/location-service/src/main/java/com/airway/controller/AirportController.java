package com.airway.controller;

import com.airway.Service.AirportService;
import com.airway.payload.reposnse.AirportResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AirportRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(@Valid @RequestBody AirportRequest request)
    {
        AirportResponse response = airportService.createAirpost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id)
    {
        AirportResponse response = airportService.getAirportById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AirportResponse>> getAllAirPort(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    )
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<AirportResponse> responses = airportService.getAllAirports(pageable);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<AirportResponse>> getAllAirportByCityId(@PathVariable Long id)
    {
        List<AirportResponse> responses = airportService.getAllAirportByCityId(id);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirportById(
            @PathVariable Long id,
            @Valid @RequestBody AirportRequest request
    )
    {
        AirportResponse response = airportService.updateAirport(id,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id)
    {
        ApiResponse response = airportService.deleteAirport(id);
        return ResponseEntity.ok(response);
    }

}
