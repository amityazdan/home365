package com.home365.assignment.service;

import com.home365.assignment.dto.DestinationDTO;
import com.home365.assignment.entities.Destination;
import com.home365.assignment.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DestinationService {

    private DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination addNewDestination(DestinationDTO location) {
        Optional<Destination> optional = destinationRepository.findFirstByName(location.getName());
        if (optional.isPresent()) {
            return optional.get();
        }
        return destinationRepository.save(new Destination(location));
    }

    public Destination getByName(String location) {
        return destinationRepository.findFirstByName(location).orElse(null);
    }

    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }
}
