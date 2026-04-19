package com.airway.controller;

import com.airway.Service.AircraftService;
import com.airway.payload.reposnse.AircraftResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AircraftRequest;
import com.airway.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircrafts")
@RequiredArgsConstructor
public class AircraftController {

    private final AircraftService aircraftService;

    @PostMapping
    public ResponseEntity<AircraftResponse> createAircraft(@Valid @RequestBody AircraftRequest request)
    {
        Long userId = SecurityUtils.getCurrentUserId();

        AircraftResponse response = aircraftService.createAircraft(request,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{aircraftId}")
    public ResponseEntity<AircraftResponse> getAircraftById(@PathVariable Long aircraftId)
    {
        AircraftResponse response = aircraftService.getAircraftById(aircraftId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AircraftResponse>> getAllAircraftByOwnerId()
    {
        Long ownerId = SecurityUtils.getCurrentUserId();
        List<AircraftResponse> responses = aircraftService.getAllAircraftByOwnerId(ownerId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{aircraftId}")
    public ResponseEntity<AircraftResponse> updateAircraftById(@Valid @RequestBody AircraftRequest request, @PathVariable Long aircraftId)
    {
        Long ownerId = SecurityUtils.getCurrentUserId();
        AircraftResponse response = aircraftService.updateAircraft(aircraftId,ownerId,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{aircraftId}")
    public ResponseEntity<ApiResponse> deleteAircraftById(@PathVariable Long aircraftId)
    {
        Long ownerId = SecurityUtils.getCurrentUserId();
        ApiResponse response = aircraftService.deleteAircraft(aircraftId,ownerId);
        return ResponseEntity.ok(response);
    }
}
