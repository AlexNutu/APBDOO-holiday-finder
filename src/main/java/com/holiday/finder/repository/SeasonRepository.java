package com.holiday.finder.repository;

import com.holiday.finder.model.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends CrudRepository<Season, Long> {

    Season getById(Long id);

    Season getByName(String name);

    List<Season> findAll();
}
