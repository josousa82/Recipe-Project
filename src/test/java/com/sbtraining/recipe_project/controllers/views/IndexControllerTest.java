package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
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
    void testMockMVC() throws Exception {
        // similar to mock server but unit testing without initializing spring context
        // another method MockMvcBuilders.webAppContextSetup() to initialize with spring context.
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        // attention with the static matchers imports
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {
        // given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        when(recipeService.getAllRecipes()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        String viewName = indexController.getIndexPage(model);

        // then
        assertEquals("index", viewName);

        // interactions tests
        verify(recipeService, times(1)).getAllRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        log.warn("Check that 2 recipes are at test" + Arrays.toString(setInController.toArray()));
        assertEquals(2, setInController.size());
    }
}