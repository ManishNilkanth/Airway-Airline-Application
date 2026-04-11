package com.airway.Service.ServiceImpl;

import com.airway.Repository.CityRepository;
import com.airway.exceptions.AirportAlreadyExistsByIADACode;
import com.airway.Repository.AirportRepository;
import com.airway.Service.AirportService;
import com.airway.exceptions.AirportNotFoundException;
import com.airway.exceptions.CityNotFoundException;
import com.airway.mapper.AirportMapper;
import com.airway.model.Airport;
import com.airway.model.City;
import com.airway.payload.reposnse.AirportResponse;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.payload.request.AirportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Override
    public AirportResponse createAirpost(AirportRequest request) {

        if(airportRepository.existsByIataCode(request.getIataCode()))
        {
            throw new AirportAlreadyExistsByIADACode(
                    String.format("Airport with given IADC code %s is already exists",request.getIataCode())
            );
        }
        City city = cityRepository.findById(request.getCityId()).orElseThrow(
                ()-> new CityNotFoundException(
                        String.format("City not found with given id %d",request.getCityId()))
                );

        Airport airport = AirportMapper.maptoAirport(request);
        airport.setCity(city);
        Airport savedAirport = airportRepository.save(airport);

        return AirportMapper.mapToAirportResponse(savedAirport);
    }

    @Override
    @Transactional(readOnly = true)
    public AirportResponse getAirportById(Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(
                ()-> new AirportNotFoundException(
                        String.format("Airport not found with airport Id %d",id)
                )
        );
        return AirportMapper.mapToAirportResponse(airport);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AirportResponse> getAllAirports(Pageable pageable) {

        return airportRepository.findAll(pageable)
                .map(AirportMapper::mapToAirportResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AirportResponse> getAllAirportByCityId(Long cityId) {
        return airportRepository.findAll()
                .stream()
                .map(AirportMapper::mapToAirportResponse)
                .toList();
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest request) {
        Airport airportFromDb = airportRepository.findById(id).orElseThrow(
                ()-> new AirportNotFoundException(
                        String.format("Airport not found with airport Id %d",id)
                )
        );
        String iadaCodeFromRequest = request.getIataCode();

        if(iadaCodeFromRequest != null && !iadaCodeFromRequest.equals(airportFromDb.getIataCode()))
        {
            if(airportRepository.existsByIataCode(iadaCodeFromRequest))
            {
                throw new AirportAlreadyExistsByIADACode(
                        String.format("Airport with given IADC code %s is already exists",iadaCodeFromRequest)
                );
            }
        }
        Airport airport = AirportMapper.uptadeAirport(airportFromDb,request);
        Airport savedAirport = airportRepository.save(airport);

        return AirportMapper.mapToAirportResponse(savedAirport);
    }

    @Override
    public ApiResponse deleteAirport(Long id) {

        if(!airportRepository.existsById(id))
        {
            throw new AirportNotFoundException(
                    String.format("Airport not found with airport Id %d",id)
            );
        }
        airportRepository.deleteById(id);
        return new ApiResponse("Airport deleted successfully");
    }
}
