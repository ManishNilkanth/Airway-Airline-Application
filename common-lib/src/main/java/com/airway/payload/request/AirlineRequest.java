package com.airway.payload.request;

import com.airway.enums.AirlineStatus;
import com.airway.payload.reposnse.Support;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AirlineRequest {

    @NotBlank(message = "iata code is required")
    @Size(min = 2,max = 2, message = "IADA code must be exactly 2 characters")
    private String iataCode;

    @NotBlank(message = "icaoCode code is required")
    private String icaoCode;

    @NotBlank(message = "name code is required")
    private String name;

    private String alias;

    private String logUrl;

    private AirlineStatus status;

    private String website;

    private String alliance;

    private Long headquartersCityId;

    private String  supportEmail;

    private String supportPhone;

    private String supportHours;

}
