package com.airway.model;


import com.airway.enums.FlightStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String flightNumber;

    @Column(nullable = false)
    private Long airlineId;

    @Column(nullable = false)
    private Long aircraftId;

    @Column(nullable = false)
    private Long arrivalAirportId;

    @Column(nullable = false)
    private Long departureAirportId;

    private FlightStatus status;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private Instant createdAt;

    @Column( nullable = false)
    @LastModifiedDate
    private Instant updatedAt;
}
