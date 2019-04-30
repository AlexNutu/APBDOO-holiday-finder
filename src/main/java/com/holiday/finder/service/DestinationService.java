package com.holiday.finder.service;

import com.holiday.finder.model.Destination;

import java.util.List;
import java.util.Optional;

public interface DestinationService {
    List<Destination> getAll();

    Optional<Destination> getById(Integer id);
}
