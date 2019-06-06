package com.holiday.finder.service;

import com.holiday.finder.model.Category;
import com.holiday.finder.model.Destination;
import com.holiday.finder.model.Place;
import com.holiday.finder.model.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    Place getById(Long placeId);

    void savePlace(Place place);

    Page<Place> getAllPlaces(Pageable page);

    Page<Place> getPlacesBySeason(Season season, Pageable page);

    Page<Place> getPlacesByDestination(Destination destination, Pageable page);

    Page<Place> getPlacesByCategories(Category category, Pageable page);

    Page<Place> getPlacesByTitleLikeOrDescriptionLike(String s, Pageable page);
}
