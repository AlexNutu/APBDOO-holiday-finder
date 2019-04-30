package com.holiday.finder.service.impl;

import com.holiday.finder.model.Destination;
import com.holiday.finder.repository.DestinationRepository;
import com.holiday.finder.service.DestinationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    DestinationRepository destinationRepository;

    @Override
    public List<Destination> getAll() {
        log.info("Retrieving all destinations");
        return destinationRepository.findAll();
    }

    @Override
    public Optional<Destination> getById(Integer id) {
        return destinationRepository.findById(Long.valueOf(id));
    }
}
