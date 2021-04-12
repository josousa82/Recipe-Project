package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.model.Category;
import com.sbtraining.recipe_project.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static com.sbtraining.recipe_project.utils.CategoryUtils.iterableCategories;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    Iterable<Category> categoryUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryUtils = iterableCategories();
        categoryService = new CategoryServiceImpl(categoryRepository);
    }


    //TODO: Improve test
    @Test
    @DisplayName("Test CategoryService method getAllDistinctCategoriesDescription()")
    void getAllDistinctCategoriesDescription() {
        when(categoryRepository.findAll()).thenReturn(categoryUtils);
        Set<String> result = categoryService.getAllDistinctCategoriesDescription();
        assertEquals( 4, result.size());
    }
}