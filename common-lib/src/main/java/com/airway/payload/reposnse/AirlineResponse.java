package com.airway.payload.reposnse;

import com.airway.enums.AirlineStatus;
import com.airway.payload.DTO.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineResponse {

    private Long id;

    private String iataCode;

    private String icaoCode;

    private String name;

    private String alias;

    private String logUrl;

    private String website;

    private String alliance;

    private Long headquartersCityId;

    private Long updatedById;

    private Long ownerId;

    private UserDTO owner;

    private Instant createdAt;

    private Instant updatedAt;

    private Support support;

    private AirlineStatus status;
}
