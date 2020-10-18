package com.home365.assignment.controller;


import com.home365.assignment.dto.AircraftDTO;
import com.home365.assignment.entities.Aircraft;
import com.home365.assignment.service.AircraftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AircraftController {

    private AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @PostMapping("/new-aircraft")
    public ResponseEntity<?> addNewAircraft(@Valid @RequestBody AircraftDTO aircraftDTO) {
        Aircraft aircraft = aircraftService.addNewAircraft(aircraftDTO);
        if (aircraft == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(aircraft, HttpStatus.CREATED);
    }
}


