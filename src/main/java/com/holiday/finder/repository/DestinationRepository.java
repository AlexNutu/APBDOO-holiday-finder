package com.holiday.finder.repository;

import com.holiday.finder.model.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, Long> {

    Destination getById(Long id);

    Destination getByName(String name);

    List<Destination> findAll();
}