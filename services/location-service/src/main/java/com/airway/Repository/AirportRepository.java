package com.airway.Repository;

import com.airway.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    boolean existsByIataCode(String iadaCode);

    List<Airport> findByCityId(Long cityId);

}
