package com.home365.assignment.service;

import com.home365.assignment.Utils.Haversine;
import com.home365.assignment.dto.AirlineDTO;
import com.home365.assignment.dto.DistancesDTO;
import com.home365.assignment.entities.Aircraft;
import com.home365.assignment.entities.Airline;
import com.home365.assignment.entities.AirlineAircraft;
import com.home365.assignment.entities.Destination;
import com.home365.assignment.repository.AirlineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AirlineService {

    private AirlineAircraftService airlineAircraftService;
    private AirlineRepository airlineRepository;
    private DestinationService destinationService;
    private AircraftService aircraftService;

    public AirlineService(AirlineAircraftService airlineAircraftService,
                          AirlineRepository airlineRepository,
                          DestinationService destinationService,
                          AircraftService aircraftService) {
        this.airlineAircraftService = airlineAircraftService;
        this.airlineRepository = airlineRepository;
        this.destinationService = destinationService;
        this.aircraftService = aircraftService;
    }

    public Airline addNewAirline(AirlineDTO airlineDTO) {
        if (airlineRepository.findFirstByName(airlineDTO.getName()) != null) {
            return null;
        }
        Destination location = destinationService.getByName(airlineDTO.getHomeBase());
        Airline airline = new Airline(airlineDTO.getName(), airlineDTO.getBalance(), location);
        return airlineRepository.save(airline);
    }

    public List<Airline> getAirlines() {
        return airlineRepository.findAll();
    }

    public ResponseEntity<?> buyAircraft(String airlineName, String aircraftName) {
        Aircraft aircraft = aircraftService.findFirstByName(aircraftName);
        if (aircraft == null) {
            log.info("Aircraft with name: {} not found", aircraftName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Airline airline = airlineRepository.findFirstByName(airlineName);
        if (airline == null) {
            log.info("Airline with name: {} not found", airlineName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean affordable = airline.updateBudget(aircraft.getPrice() * -1);
        if (!affordable) {
            log.info("Airline with name: {} dont have enough money", airlineName);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        airlineAircraftService.addAirlineAircraft(airline, aircraft);
        airlineRepository.save(airline);
        log.info("Airline with name: {} bought {}", airlineName, aircraftName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public Airline sellAircraft(Long airlineAircraftId) {
        Airline airline = airlineAircraftService.sellAircraft(airlineAircraftId);
        if (airline == null) {
            return null;
        }
        return airlineRepository.save(airline);
    }

    public List<DistancesDTO> distances(String airlineName) {
        Airline airline = airlineRepository.findFirstByName(airlineName);
        if (airline == null) {
            log.info("No airline with name {}", airlineName);
            return null;
        }
        return destinationService.getAll()
                .stream()
                .filter(d -> !StringUtils.equals(d.getName(), airline.getHomeBase().getName()))
                .map(d -> {
                    return new DistancesDTO(d.getName(), (int) Haversine.distance(
                            airline.getHomeBase().getLatitude(),
                            airline.getHomeBase().getLongitude(),
                            d.getLatitude(),
                            d.getLongitude()
                    ));
                }).collect(Collectors.toList());
    }

    public List<DistancesDTO> availableDestination(String airlineName) {
        Airline airline = airlineRepository.findFirstByName(airlineName);
        if (airline == null || airline.getAirlineAircraft().size() == 0) {
            log.info("There is no airline with name {} || airline dont have any aircraft", airlineName);
            return null;
        }

        int max = airline.getAirlineAircraft()
                .stream()
                .distinct()
                .mapToInt(v -> v.getAircraft().getMaxDistance())
                .max().getAsInt();

        return distances(airlineName)
                .stream()
                .filter(distancesDTO -> distancesDTO.getDistance() < max)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> ownershipTransfer(Long airlineAircraftId, Long newOwnerId) {
        Airline newOwner = airlineRepository.findById(newOwnerId).orElse(null);
        if (newOwner == null) {
            log.info("airline_aircraft_id: {} not exists", airlineAircraftId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AirlineAircraft airlineAircraft = airlineAircraftService.getAirlineAircraft(airlineAircraftId);
        if (newOwner == airlineAircraft.getAirline()) {
            log.info("airline_aircraft_id: {} is already owned by {}", airlineAircraftId, newOwnerId);
            return new ResponseEntity<>("Already own this aircraft", HttpStatus.CONFLICT);
        }
        int sellPrice = airlineAircraft.getSellPrice();
        boolean affordable = newOwner.updateBudget(sellPrice * -1);
        if (!affordable) {
            log.info("{} dont have enough money", newOwner.getName());
            return new ResponseEntity<>(newOwner.getName() + "dont have enough money", HttpStatus.BAD_REQUEST);
        }
        Airline oldOwner = airlineAircraft.getAirline();
        oldOwner.updateBudget(sellPrice);
        airlineRepository.save(oldOwner);

        airlineAircraftService.ownershipTransfer(airlineAircraft, newOwner);

        airlineRepository.save(newOwner);
        return new ResponseEntity<>("Aircraft transfer was made", HttpStatus.OK);
    }
}
