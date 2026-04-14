package com.airway.payload.reposnse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineDropdownItem {

    private Long id;

    private String  name;

    private String iadaCode;

    private String icaoCode;

    private String logoUrl;

    private String country;
}
