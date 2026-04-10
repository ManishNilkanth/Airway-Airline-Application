package com.airway.payload.reposnse;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse {

    private Long id;

    private String name;

    private  String cityCode;

    private String countryName;

    private String countryCode;

    private String regionCode;

    private String timeZoneOffset;
}
