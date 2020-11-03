package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeViewsControllerTest {

    RecipeViewsController recipeViewsControllerVictim;

    @Mock
    RecipeService recipeServiceMock;

    @Mock
    Model modelMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeViewsControllerVictim = new RecipeViewsController(recipeServiceMock);
    }

    @Test
    void getRecipe() throws Exception {

        Recipe recipe1 = Recipe.builder().id(1L).build();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeViewsControllerVictim).build();

        when(recipeServiceMock.getRecipeById(anyLong())).thenReturn(recipe1);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.view().name("recipe/show")).andExpect(model().attributeExists("recipe"));
    }
}