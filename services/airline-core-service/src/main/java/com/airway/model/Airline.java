package com.airway.model;

import com.airway.enums.AirlineStatus;
import com.airway.payload.reposnse.Support;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false)
    private String iataCode;

    @Column(unique = true,nullable = false)
    private String icaoCode;

    @Column(nullable = false)
    private String name;

    private String alias;

    private String logUrl;

    private String website;

    @Enumerated(EnumType.STRING)
    private AirlineStatus status = AirlineStatus.ACTIVE;

    private String alliance;

    private Long headquartersCityId;

    private Long updatedById;

    @Column(nullable = false)
    private Long ownerId;

    @CreatedDate
    @Column(unique = true,nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    @Embedded
    private Support support;
}
