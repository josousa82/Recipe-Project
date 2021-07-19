package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.services.CategoryService;
import com.sbtraining.recipe_project.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
@ExtendWith(SpringExtension.class)
@SpringBootTest
class IndexControllerViewTest {

    // Under Test
    IndexController indexController;

    @Mock
    RecipeServiceImpl recipeService;

    @Mock
    CategoryService categoryService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService, categoryService);
    }

    @Test
    void testMockMVC() throws Exception {

        // similar to mock server but unit testing without initializing spring context
        // another method MockMvcBuilders.webAppContextSetup() to initialize with spring context.

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        // attention with the static matchers imports
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andExpect(view().name("index2"));
    }

    @Test
    void getIndexPage() {
        // given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(Recipe.builder().id(1L).build());
        recipes.add(Recipe.builder().id(2L).build());

        when(recipeService.getAllRecipes()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        String viewName = indexController.getIndexPage(model);

        // then
        assertEquals("index2", viewName);

        // interactions tests
        verify(recipeService, times(1)).getAllRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        log.warn("Check that 2 recipes are at test" + Arrays.toString(setInController.toArray()));
        assertEquals(2, setInController.size());
    }
}