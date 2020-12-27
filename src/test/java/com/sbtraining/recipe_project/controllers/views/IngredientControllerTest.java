package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.services.IngredientService;
import com.sbtraining.recipe_project.services.RecipeService;
import com.sbtraining.recipe_project.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        List<IngredientCommand> ingredientCommandList = List.of(
                IngredientCommand.builder()
                                 .id(1L)
                                 .build(),

                IngredientCommand.builder()
                                 .id(2L)
                                 .build()
        );
        when(ingredientService.findAllRecipeIngredientsByRecipeId(anyLong())).thenReturn(ingredientCommandList);

        //when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/list"))
                .andExpect(model().attributeExists("recipeIngredients"))
                .andExpect(model().attributeExists("recipeId"))
                .andExpect(model().size(2));

        //then
        verify(ingredientService, times(1)).findAllRecipeIngredientsByRecipeId(anyLong());
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
                Set.of(UnitOfMeasureCommand.builder().id(1L).build(), UnitOfMeasureCommand.builder().id(2L).build()));

        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/ingredientForm"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        assertEquals(2, unitOfMeasureService.listAllUoms().size());

        verify(unitOfMeasureService, times(2)).listAllUoms();
        verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
    }

    @Test
    void saveOrUpdate() throws Exception {
        command.setId(3L)
               .setRecipeId(2L);
        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

    }

    @Test
    void createNewIngredient() throws Exception {
        RecipeCommand recipeCommand = RecipeCommand.builder()
                .id(1L)
                .build();

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("recipe/ingredients/ingredientForm"))
               .andExpect(model().attributeExists("ingredient"))
               .andExpect(model().attributeExists("uomList"));
        verify(recipeService, times(1)).findCommandById(anyLong());
    }


    @Test
    void deleteIngredient() throws Exception {

        //when
        mockMvc.perform(get("/recipe/1/ingredient/2/delete"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/recipe/1/ingredients"));

        //then
        verify(ingredientService, times(1)).deleteByRecipeIdAndId(anyLong(), anyLong());
    }
}