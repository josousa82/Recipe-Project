package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.model.UnitOfMeasure;
import com.sbtraining.recipe_project.services.IngredientService;
import com.sbtraining.recipe_project.services.RecipeService;
import com.sbtraining.recipe_project.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class IngredientControllerTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "flower";
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Long UOM_ID = 1L;
    public static final Recipe RECIPE = Recipe.builder().id(1L).build();

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController ingredientController;

    MockMvc mockMvc;

    IngredientCommand command;

    UnitOfMeasureCommand uomCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);

        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        uomCommand = UnitOfMeasureCommand.builder()
                .id(UOM_ID)
                .build();

        command = IngredientCommand.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .amount(AMOUNT)
                .uom(uomCommand)
                .recipeId(RECIPE.getId())
                .build();
    }

    @Test
    void testListIngredients() throws Exception {

        //given
        when(recipeService.findCommandById(anyLong())).thenReturn(RecipeCommand.builder()
                .id(1L)
                .build());

        //when

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/list"))
                .andExpect(model().attributeExists("recipe"));
        //then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }


    @Test
    void testShowRecipeIngredient() throws Exception {

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/show"))
                .andExpect(model().attributeExists("ingredient"));

        verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
    }

    @Test
    void testUpdateRecipeIngredient() throws Exception {

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
        when(unitOfMeasureService.listAllUoms()).thenReturn(
                List.of(UnitOfMeasure.builder().id(1L).build(), UnitOfMeasure.builder().id(2L).build()));

        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/update"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        assertEquals(unitOfMeasureService.listAllUoms().size(), 2);
        verify(unitOfMeasureService, times(2)).listAllUoms();

        verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());


    }
}