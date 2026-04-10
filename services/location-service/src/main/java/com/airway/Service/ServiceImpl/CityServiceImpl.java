package com.airway.Service.ServiceImpl;

import com.airway.Exceptions.CityAlreadyExistsByCityCodeException;
import com.airway.Exceptions.CityNotFoundException;
import com.airway.Repository.CityRepository;
import com.airway.Service.CityService;
import com.airway.mapper.CityMapper;
import com.airway.model.City;
import com.airway.payload.reposnse.CityResponse;
import com.airway.payload.request.CityRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityResponse createCity(CityRequest request) {

        if(cityRepository.existsByCityCode(request.getCityCode()))
        {
            throw new CityAlreadyExistsByCityCodeException("city with given city code is already exists");
        }
        City city = cityRepository.save(CityMapper.mapToCity(request));

        return CityMapper.mapToCityResponse(city);
    }

    @Override
    @Transactional(readOnly = true)
    public CityResponse getCityById(Long id) {

        City city = cityRepository.findById(id)
                .orElseThrow(()-> new CityNotFoundException(
                        String.format("City not found with city Id %d",id)
                ));
        return CityMapper.mapToCityResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) {

        City city = cityRepository.findById(id)
                .orElseThrow(()-> new CityNotFoundException(
                        String.format("City not found with city Id %d",id)
                ));
        City updatedCity = CityMapper.updateCity(city,request);
        City savedCity = cityRepository.save(updatedCity);

        return CityMapper.mapToCityResponse(savedCity);
    }

    @Override
    public void deleteCity(Long id) {

        if(!cityRepository.existsById(id))
        {
            throw new CityNotFoundException(
                    String.format("City not found with city Id %d",id)
            );
        }
        cityRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CityResponse> getAllCities(Pageable pageable) {

        return cityRepository.findAll(pageable).map(CityMapper::mapToCityResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword, pageable).map(CityMapper::mapToCityResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode,pageable).map(CityMapper::mapToCityResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
