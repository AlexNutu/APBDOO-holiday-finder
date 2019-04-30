package com.holiday.finder.service;

import com.holiday.finder.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();

    Category saveCategory(Category category);

    Optional<Category> getById(Integer integer);
}
