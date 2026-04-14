package com.airway.mapper;

import com.airway.model.City;
import com.airway.payload.reposnse.CityResponse;
import com.airway.payload.request.CityRequest;

public class CityMapper {

    public static City mapToCity(CityRequest request)
    {
        if(request == null) return  null;

        return City.builder()
                .name(request.getName())
                .cityCode(request.getCityCode())
                .countryName(request.getCountryName())
                .countryCode(request.getCountryCode())
                .regionCode(request.getRegionCode())
                .timeZoneId(request.getTimeZoneOffset())
                .build();
    }

    public static CityResponse mapToCityResponse(City city)
    {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .cityCode(city.getCityCode())
                .countryName(city.getCountryName())
                .countryCode(city.getCountryCode())
                .regionCode(city.getRegionCode())
                .timeZoneOffset(city.getTimeZoneId())
                .build();
    }

    public static City updateCity(City city, CityRequest request)
    {
        if(request == null) return city;

        if(request.getName() != null) city.setName(request.getName());

        if(request.getCityCode() != null) city.setCityCode(request.getCityCode().toUpperCase().trim());

        if(request.getCountryName() != null) city.setCountryName(request.getCountryName());

        if(request.getCountryCode() != null) city.setCountryCode(request.getCountryCode().toUpperCase().trim());

        if(request.getRegionCode() != null) city.setRegionCode(request.getRegionCode().toUpperCase().trim());

        if(request.getTimeZoneOffset() != null) city.setTimeZoneId(request.getTimeZoneOffset().toUpperCase().trim());

        return city;
    }
}
