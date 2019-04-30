package com.holiday.finder.repository;

import com.holiday.finder.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country getById(Long id);
    Country getByName(String name);
}
