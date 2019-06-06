package com.holiday.finder.repository;

import com.holiday.finder.model.Category;
import com.holiday.finder.model.Destination;
import com.holiday.finder.model.Place;
import com.holiday.finder.model.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {

    Place getById(Long id);

    Place getByTitle(String name);

    Page<Place> findByTitleContains(String title, Pageable p);    // for search

    Page<Place> findByTitleLike(String title, Pageable p);

    Page<Place> findByIdIn(List<Long> ids, Pageable p);

    Page<Place> findBySeason(Season s, Pageable p);

    Page<Place> findByDestination(Destination d, Pageable p);

    Page<Place> findByMinPriceBetween(double p1, double p2, Pageable p);     // for budget filter

    @Query("select title, description from Place")
    List<Object[]> findAllpartial();

    @Query("from Place where titlu = :title")
    List<Place> findByTitle(@Param("title") String title);

    Page<Place> findByCategoriesIn(Category c, Pageable p);

    Page<Place> findByTitleLikeOrDescriptionLike(String s, String sameString, Pageable page);


}
