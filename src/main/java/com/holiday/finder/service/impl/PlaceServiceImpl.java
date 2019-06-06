package com.holiday.finder.service.impl;

import com.holiday.finder.model.Category;
import com.holiday.finder.model.Destination;
import com.holiday.finder.model.Place;
import com.holiday.finder.model.Season;
import com.holiday.finder.repository.PlaceRepository;
import com.holiday.finder.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Override
    public Place getById(Long placeId) {
        log.info("Finding place by Id");
        return placeRepository.getById(placeId);
    }

    @Override
    public void savePlace(Place place) {
        placeRepository.save(place);
    }

    @Override
    public Page<Place> getAllPlaces(Pageable pageable) {
        Page<Place> placePage = placeRepository.findAll(pageable);
        return placePage;
    }

    @Override
    public Page<Place> getPlacesBySeason(Season season, Pageable page) {
        return placeRepository.findBySeason(season, page);
    }

    @Override
    public Page<Place> getPlacesByDestination(Destination destination, Pageable page) {
        return placeRepository.findByDestination(destination, page);
    }

    @Override
    public Page<Place> getPlacesByCategories(Category category, Pageable page) {
        return placeRepository.findByCategoriesIn(category, page);
    }

    @Override
    public Page<Place> getPlacesByTitleLikeOrDescriptionLike(String s, Pageable page) {
        return placeRepository.findByTitleLikeOrDescriptionLike(s, s, page);
    }
}
