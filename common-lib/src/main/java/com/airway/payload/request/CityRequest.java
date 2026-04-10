package com.airway.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {

    @NotBlank(message = "City name is required")
    private String name;

    @NotBlank(message = "cityCode name is required")
    @Size(max = 10)
    private  String cityCode;

    @NotBlank(message = "countryName name is required")
    @Size(max = 100)
    private String countryName;

    @NotBlank(message = "countryCode name is required")
    @Size(max = 10)
    private String countryCode;

    @Size(max = 10)
    private String regionCode;

    @Size(max = 10)
    private String timeZoneOffset;
}
