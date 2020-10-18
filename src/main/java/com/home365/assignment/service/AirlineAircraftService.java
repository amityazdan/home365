package com.home365.assignment.service;

import com.home365.assignment.entities.Aircraft;
import com.home365.assignment.entities.Airline;
import com.home365.assignment.entities.AirlineAircraft;
import com.home365.assignment.repository.AirlineAircraftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AirlineAircraftService {

    private AirlineAircraftRepository airlineAircraftRepository;

    public AirlineAircraftService(AirlineAircraftRepository airlineAircraftRepository) {
        this.airlineAircraftRepository = airlineAircraftRepository;
    }

    public AirlineAircraft addAirlineAircraft(Airline airline, Aircraft aircraft) {
        AirlineAircraft airlineAircraft = new AirlineAircraft(airline, aircraft);
        return airlineAircraftRepository.save(airlineAircraft);
    }


    public Airline sellAircraft(Long airlineAircraftId) {
        AirlineAircraft airlineAircraft = airlineAircraftRepository.findById(airlineAircraftId).orElse(null);
        if (airlineAircraft == null) {
            log.info("No airlineAircraft with id {} ", airlineAircraftId);
            return null;
        }
        Airline airline = airlineAircraft.getAirline();
        airline.updateBudget(airlineAircraft.getSellPrice());
        airlineAircraftRepository.delete(airlineAircraft);
        return airline;
    }

    public AirlineAircraft getAirlineAircraft(Long airlineAircraftId) {
        return airlineAircraftRepository.findById(airlineAircraftId).orElse(null);
    }

    public void ownershipTransfer(AirlineAircraft airlineAircraft, Airline newOwner) {
        airlineAircraft.setAirline(newOwner);
        airlineAircraftRepository.save(airlineAircraft);
    }
}
