package com.home365.assignment.service;

import com.home365.assignment.dto.AircraftDTO;
import com.home365.assignment.entities.Aircraft;
import com.home365.assignment.repository.AircraftRepository;
import org.springframework.stereotype.Service;


@Service
public class AircraftService {

    private AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public Aircraft addNewAircraft(AircraftDTO aircraftDTO){
        if (aircraftRepository.findFirstByName(aircraftDTO.getName()) != null){
            return null;
        }
        Aircraft aircraft = new Aircraft(aircraftDTO);
        return aircraftRepository.save(aircraft);
    }

    public Aircraft findFirstByName(String name){
        return aircraftRepository.findFirstByName(name);
    }


}
