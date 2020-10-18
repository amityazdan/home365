package com.home365.assignment.service;

import com.home365.assignment.dto.DestinationDTO;
import com.home365.assignment.entities.Destination;
import com.home365.assignment.repository.DestinationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class DestinationService {

    private DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination addNewDestination(DestinationDTO destinationDTO) {
        Optional<Destination> optional = destinationRepository.findFirstByName(destinationDTO.getName());
        if (optional.isPresent()) {
            log.info("Destination with name: {} already exists", destinationDTO.getName());
            return optional.get();
        }
        log.info("Destination with name: {} created successfuly", destinationDTO.getName());
        return destinationRepository.save(new Destination(destinationDTO));
    }

    public Destination getByName(String location) {
        return destinationRepository.findFirstByName(location).orElse(null);
    }

    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }
}
