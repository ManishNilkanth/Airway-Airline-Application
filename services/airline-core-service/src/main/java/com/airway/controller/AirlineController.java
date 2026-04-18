package com.airway.controller;

import com.airway.Service.AirlineService;
import com.airway.enums.AirlineStatus;
import com.airway.payload.reposnse.AirlineDropdownItem;
import com.airway.payload.reposnse.AirlineResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AirlineRequest;
import com.airway.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.airway.security.SecurityUtils.getCurrentUserId;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(
            @Valid @RequestBody AirlineRequest request
    ) {
        Long ownerId = SecurityUtils.getCurrentUserId();
        AirlineResponse response = airlineService.createAirline(request, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/admin")
    public ResponseEntity<AirlineResponse> getAirlineByOwner() {
        Long ownerId = SecurityUtils.getCurrentUserId();

        AirlineResponse response = airlineService.getAirlineByOwner(ownerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlineByAirlineId(@PathVariable Long id) {
        AirlineResponse response = airlineService.getAirlineByOwner(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getAllAirlines(Pageable pageable) {
        return ResponseEntity.ok(airlineService.getAllAirlines(pageable));
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<AirlineDropdownItem>> getAirlinesForDropdown() {
        return ResponseEntity.ok(airlineService.getAirlineDropdown());
    }

    @PutMapping
    public ResponseEntity<AirlineResponse> updateAirline(
            @Valid @RequestBody AirlineRequest request
    ) {
        Long ownerId = SecurityUtils.getCurrentUserId();

        AirlineResponse response = airlineService.updateAirline(request, ownerId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirlineById(@PathVariable Long id) {
        Long ownerId = SecurityUtils.getCurrentUserId();

        return ResponseEntity.ok(airlineService.deleteAirLine(id, ownerId));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<AirlineResponse> approveAirline(@PathVariable Long id) {
        return ResponseEntity.ok(
                airlineService.changeStatusByAdmin(id, AirlineStatus.ACTIVE)
        );
    }

    @PostMapping("/{id}/suspend")
    public ResponseEntity<AirlineResponse> suspendAirline(@PathVariable Long id) {
        return ResponseEntity.ok(
                airlineService.changeStatusByAdmin(id, AirlineStatus.INACTIVE)
        );
    }

    @PostMapping("/{id}/ban")
    public ResponseEntity<AirlineResponse> banAirline(@PathVariable Long id) {
        return ResponseEntity.ok(
                airlineService.changeStatusByAdmin(id, AirlineStatus.BANNED)
        );
    }
}