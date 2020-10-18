package com.home365.assignment.repository;

import com.home365.assignment.entities.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    Aircraft findFirstByName(String name);
}
