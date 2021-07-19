package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImpITTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }


//    TODO: Improve test
    @Test
    @DisplayName("Test CategoryService method getAllDistinctCategoriesDescription()")
    void getAllDistinctCategoriesDescription() {

    }
}