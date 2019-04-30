package com.holiday.finder.repository;

import com.holiday.finder.model.Category;
import org.hibernate.Hibernate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    public static final String NAME = "Luxury";

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Ignore
    public void testTrueLazyLoading() {
        Category category = categoryRepository.findByName(NAME);
        assertFalse(Hibernate.isInitialized(category.getPlaces()));
    }

    @Test
    public void testFalseLazyLoading() {
        Category category = categoryRepository.findByName(NAME);
        assertTrue(Hibernate.isInitialized(category.getPlaces()));
    }

}
