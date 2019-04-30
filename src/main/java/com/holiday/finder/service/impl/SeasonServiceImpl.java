package com.holiday.finder.service.impl;

import com.holiday.finder.model.Season;
import com.holiday.finder.repository.SeasonRepository;
import com.holiday.finder.service.SeasonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    SeasonRepository seasonRepository;

    @Override
    public List<Season> getAll() {
        log.info("Retrieving all seasons");
        return seasonRepository.findAll();
    }

    @Override
    public Optional<Season> getById(Integer id) {
        return seasonRepository.findById(Long.valueOf(id));
    }
}
