package com.holiday.finder.repository;

import com.holiday.finder.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findById(Long id);
    Category findByName(String name);
    List<Category> findAll();

}
