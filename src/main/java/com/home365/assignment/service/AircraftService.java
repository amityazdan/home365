package com.home365.assignment.service;

import com.home365.assignment.dto.AircraftDTO;
import com.home365.assignment.entities.Aircraft;
import com.home365.assignment.repository.AircraftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AircraftService {

    private AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public Aircraft addNewAircraft(AircraftDTO aircraftDTO){
        if (aircraftRepository.findFirstByName(aircraftDTO.getName()) != null){
            log.info("aircraft already exists");
            return null;
        }
        return aircraftRepository.save(new Aircraft(aircraftDTO));
    }

    public Aircraft findFirstByName(String name){
        return aircraftRepository.findFirstByName(name);
    }


}
