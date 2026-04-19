package com.airway.repository;

import com.airway.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    boolean existsByCode(String code);

    Aircraft findByIdAndAirlineId(Long aircraftId, Long id);

    List<Aircraft> findByAirlineId(Long id);
}
