package com.holiday.finder.service;

import com.holiday.finder.model.Category;
import com.holiday.finder.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    public static final String NAME = "New Category";

    @Autowired
    CategoryService categoryService;

    @Before
    public void initTest() {
        log.info("This is BEFORE running saveCategory test");
    }

    @Test
    public void saveCategoryTest() {

        log.info("Saving new Category ...");

        Category newCategory = new Category();
        newCategory.setName(NAME);

        Category savedCategory = categoryService.saveCategory(newCategory);

        assertEquals(NAME, savedCategory.getName());

        log.info("New category saved!");

    }

    @After
    public void afterTest() {
        log.info("This is AFTER running saveCategory test");
    }

}
