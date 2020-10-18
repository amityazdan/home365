package com.home365.assignment.repository;

import com.home365.assignment.entities.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Airline findFirstByName(String name);
}
