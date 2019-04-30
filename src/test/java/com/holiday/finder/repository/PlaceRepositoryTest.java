package com.holiday.finder.repository;

import com.holiday.finder.model.Destination;
import com.holiday.finder.model.Place;
import com.holiday.finder.model.Season;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PlaceRepositoryTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private DestinationRepository destinationRepository;


    @Test
    public void findTest() {

        Pageable pageable = PageRequest.of(0, 2);

        Page<Place> placePage = placeRepository.findByTitleContains("Picchu", pageable);
        log.info("=== findByTitleContains ===");
        placePage.forEach(place -> log.info(place.getTitle()));

        placePage = placeRepository.findByTitleLike("Brasov%", pageable);
        log.info("=== findByTitleLike ===");
        placePage.forEach(place -> log.info(place.getTitle()));

        placePage = placeRepository.findByIdIn(Arrays.asList(1L, 2L), pageable);
        log.info("=== findByIdIn ===");
        placePage.forEach(place -> log.info(place.getId() + " " + place.getTitle()));

        Season testSeason = seasonRepository.getByName("Summer");
        placePage = placeRepository.findBySeason(testSeason, pageable);
        log.info("=== findBySeason ===");
        placePage.forEach(place -> log.info(place.getTitle()));

        Destination testDestination = destinationRepository.getByName("Europe");
        placePage = placeRepository.findByDestination(testDestination, pageable);
        log.info("==== findByDestination ===");
        placePage.forEach(place -> log.info(place.getTitle()));

        placePage = placeRepository.findByMinPriceBetween(100.0d, 200.0d, pageable);
        log.info("=== findByMinPriceBetween ===");
        placePage.forEach(place -> log.info(place.getTitle()));

    }

    @Test
    public void testFindAllPageable() {
        log.info("=== testFindAllPageable ===");
        Pageable pageable = PageRequest.of(0, 2);
        Page<Place> placePage = placeRepository.findAll(pageable);
        placePage.forEach(place -> log.info(place.getTitle()));
    }

    @Test
    public void testFindAllSort() {
        log.info("=== testFindAllSort ===");
        placeRepository.findAll(Sort.by("title").descending()).
                forEach(place -> log.info(place.getTitle()));
        placeRepository.findAll(Sort.by("title").ascending().by("description").descending()).
                forEach(place -> log.info(place.getTitle()));
    }

    @Test
    public void testFindAllPageAndSort() {
        log.info("=== testFindAllPageAndSort ===");
        Sort sort = Sort.by("title").descending();
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<Place> placePage = placeRepository.findAll(pageable);
        placePage.forEach(place -> log.info(place.getTitle()));

    }


}
