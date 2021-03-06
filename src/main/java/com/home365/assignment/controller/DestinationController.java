package com.home365.assignment.controller;


import com.home365.assignment.dto.DestinationDTO;
import com.home365.assignment.entities.Destination;
import com.home365.assignment.service.DestinationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class DestinationController {


    private DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/new-destination")
    public ResponseEntity<?> addNewDestination(@Validated @RequestBody DestinationDTO destinationDTO) {
        log.info("Rest request for new destination with name: {}", destinationDTO.getName());
        Destination destination = destinationService.addNewDestination(destinationDTO);
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }
}
