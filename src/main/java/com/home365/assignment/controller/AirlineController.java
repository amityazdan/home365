package com.home365.assignment.controller;


import com.home365.assignment.dto.AirlineDTO;
import com.home365.assignment.dto.DistancesDTO;
import com.home365.assignment.entities.Airline;
import com.home365.assignment.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/airline")
public class AirlineController {

    private AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> addNewAirline(@RequestBody AirlineDTO airlineDTO) {
        log.info("Rest request for new airline with name: {}", airlineDTO.getName());
        Airline airline = airlineService.addNewAirline(airlineDTO);
        if (airline == null) {
            log.info("Airline with name: {} already exists", airlineDTO.getName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        log.info("Airline with name: {} created", airlineDTO.getName());
        return new ResponseEntity<>(airline, HttpStatus.OK);
    }

    @PutMapping("/buy-aircraft/{airline}/{aircraft}")
    public ResponseEntity<?> buyAircraft(@PathVariable String airline, @PathVariable String aircraft) {
        log.info("Rest request for buying new aircraft: {}, for airline: {}", aircraft, airline);
        return airlineService.buyAircraft(airline, aircraft);
    }

    @PutMapping("/sell-aircraft/{airline_aircraft_id}")
    public ResponseEntity<?> sellAircraft(@PathVariable Long airline_aircraft_id) {
        log.info("Rest request for selling 'airline_aircraft_id': {}", airline_aircraft_id);
        Airline airline = airlineService.sellAircraft(airline_aircraft_id);
        if (airline == null) {
            return new ResponseEntity<>("No aircraft instance with this id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(airline, HttpStatus.OK);
    }

    @PutMapping("/ownership-transfer/{airline_aircraft_id}/{new_owner_id}")
    public ResponseEntity<?> ownershipTransfer(@PathVariable Long airline_aircraft_id, @PathVariable Long new_owner_id) {
        log.info("Rest request for transfer ownership airline_aircraft_id: {}, new_owner_id: {}", airline_aircraft_id, new_owner_id);
        return airlineService.ownershipTransfer(airline_aircraft_id, new_owner_id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAirlines() {
        log.info("Rest request for getting all airlines");
        List<Airline> airlines = airlineService.getAirlines();
        return new ResponseEntity<>(airlines, HttpStatus.OK);
    }


    @GetMapping("/{airline}/distances")
    public ResponseEntity<?> distances(@PathVariable String airline) {
        log.info("Rest request for getting all distances for airline {}", airline);
        List<DistancesDTO> destinations = airlineService.distances(airline);
        if (destinations == null) {
            return new ResponseEntity<>("No airline with name " + airline, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/{airline}/available-destination")
    public ResponseEntity<?> availableDestination(@PathVariable String airline) {
        log.info("Rest request for getting all available destination for airline {}", airline);
        List<DistancesDTO> distancesDTOS = airlineService.availableDestination(airline);
        if (distancesDTOS == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(distancesDTOS, HttpStatus.OK);
    }


}
