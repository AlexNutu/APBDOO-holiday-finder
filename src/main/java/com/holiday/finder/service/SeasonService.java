package com.holiday.finder.service;

import com.holiday.finder.model.Season;

import java.util.List;
import java.util.Optional;

public interface SeasonService {
    List<Season> getAll();

    Optional<Season> getById(Integer id);
}
