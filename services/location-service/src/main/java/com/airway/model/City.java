package com.airway.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private  String cityCode;

    @Column(nullable = false,unique = true)
    private String countryCode;

    @Column(nullable = false)
    private String countryName;

    private String regionCode;

    @Column(name = "time_zone_id", length = 50)
    private String timeZoneId;
}
