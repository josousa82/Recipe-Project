package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    // Under Test
    IndexController indexController;

    @Mock
    RecipeServiceImpl recipeService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {

        String index = "index";
        assertEquals(indexController.getIndexPage(model), index);

        // interactions tests
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
        verify(recipeService, times(1)).getAllRecipes();
    }
}