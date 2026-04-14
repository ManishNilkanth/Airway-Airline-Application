package com.airway.repository;

import com.airway.enums.AirlineStatus;
import com.airway.model.Airline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Long> {

    Optional<Airline> findByOwnerId(Long ownerId);

    Page<Airline> findAllByOwnerId(Pageable pageable,Long ownerId);

    List<Airline> findByStatus(AirlineStatus status);

    boolean existsByOwnerId(Long ownerId);
}
