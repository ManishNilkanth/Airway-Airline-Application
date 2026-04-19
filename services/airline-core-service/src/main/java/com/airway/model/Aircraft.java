package com.airway.model;

import com.airway.enums.AircraftStatus;
import com.airway.exceptions.SeatCountMissMatchException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        indexes = {
                @Index(name = "idx_aircraft_status", columnList = "status"),
                @Index(name = "idx_aircraft_code",columnList = "code")
        }
)

public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String code;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private Integer seatingCapacity;

    @Column(nullable = false, name = "economy_seats")
    private Integer economySeats = 0;

    @Column(nullable = false, name = "premium_economy_seats")
    private Integer premiumEconomySeats = 0;

    @Column(nullable = false, name = "business_seats")
    private Integer businessSeats = 0;

    @Column(nullable = false,name = "first_class_seats")
    private Integer firstClassSeats = 0;

    @Column(name = "range_km")
    private Integer rangeKm;

    @Column(name = "cruising_speed_kmh")
    private Integer cruisingSpeedKmh;

    private Integer maxAltitude;

    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;

    private LocalDate registrationDate;

    private LocalDate nextMaintenanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AircraftStatus status = AircraftStatus.ACTIVE;

    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    @JsonIgnore
    private Airline airline;

    private Long currentAirportId;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Integer getTotalSeats() {
        return economySeats + premiumEconomySeats + businessSeats +  firstClassSeats;
    }

    @PrePersist
    @PreUpdate
    public void validateSeats()
    {
        if(seatingCapacity != null && !seatingCapacity.equals(getTotalSeats()))
        {
            throw new SeatCountMissMatchException("seat count mismatch");
        }
    }

    public boolean isOperational() {
        return status == AircraftStatus.ACTIVE;
    }

    public boolean isMaintenanceDueSoon() {
        if (nextMaintenanceDate == null) {
            return false;
        }
        return !nextMaintenanceDate.isAfter(LocalDate.now().plusWeeks(2));
    }

}
