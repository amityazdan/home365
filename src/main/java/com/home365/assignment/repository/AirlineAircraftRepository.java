package com.home365.assignment.repository;

import com.home365.assignment.entities.AirlineAircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineAircraftRepository extends JpaRepository<AirlineAircraft, Long> {
}
