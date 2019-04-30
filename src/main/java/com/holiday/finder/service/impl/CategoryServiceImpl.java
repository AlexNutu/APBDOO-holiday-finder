package com.holiday.finder.service.impl;

import com.holiday.finder.model.Category;
import com.holiday.finder.repository.CategoryRepository;
import com.holiday.finder.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        log.info("Retrieving all categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getById(Integer id) {
        return categoryRepository.findById(Long.valueOf(id));
    }

}
