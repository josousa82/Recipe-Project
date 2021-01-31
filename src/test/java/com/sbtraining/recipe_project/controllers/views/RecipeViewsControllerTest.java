package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.controllers.exceptions.ControllerExceptionHandler;
import com.sbtraining.recipe_project.converters.*;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.model.*;
import com.sbtraining.recipe_project.model.enums.Difficulty;
import com.sbtraining.recipe_project.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static com.sbtraining.recipe_project.controllers.views.ViewsLinksEnum.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeViewsControllerTest {

    public static final Long RECIPE_ID = 1L;
    public static final String RECIPE_DESCRIPTION = "flower";
    public static final Integer RECIPE_PREP_TIME = 30;
    public static final Integer RECIPE_COOK_TIME = 15;
    public static final Integer RECIPE_SERVINGS = 4;
    public static final String RECIPE_SOURCE = "flower";
    public static final String RECIPE_URL = "http://test.com";
    public static final String RECIPE_DIRECTIONS = "flower";
    public static final Difficulty RECIPE_DIFFICULTY = Difficulty.EASY;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGREDIENT_ID_1 = 1L;
    public static final Long INGREDIENT_ID_2 = 2L;
    public static final Long NOTES_ID_1 = 8L;

    RecipeViewsController recipeViewsControllerVictim;

    RecipeToRecipeCommand recipeToRecipeCommand;

    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeService recipeServiceMock;

    @Mock
    Model modelMock;

    Recipe recipe1;

    RecipeCommand recipeCommand;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        recipeViewsControllerVictim = new RecipeViewsController(recipeServiceMock);

        var notes = Notes.builder()
                .id(NOTES_ID_1)
                .build();

        recipe1 = Recipe.builder()
                .id(RECIPE_ID)
                .description(RECIPE_DESCRIPTION)
                .prepTime(RECIPE_PREP_TIME)
                .cookTime(RECIPE_COOK_TIME)
                .servings(RECIPE_SERVINGS)
                .source(RECIPE_SOURCE)
                .url(RECIPE_URL)
                .directions(RECIPE_DIRECTIONS)
                .difficulty(RECIPE_DIFFICULTY)
                .notes(notes)
                .build();

        recipe1.addIngredient(Ingredient.builder().id(INGREDIENT_ID_1).uom(UnitOfMeasure.builder().id(INGREDIENT_ID_1).build()).build());
        recipe1.addIngredient(Ingredient.builder().id(INGREDIENT_ID_2).uom(UnitOfMeasure.builder().id(INGREDIENT_ID_2).build()).build());

        recipe1.addCategory(Category.builder().id(CAT_ID_1).build());
        recipe1.addCategory(Category.builder().id(CAT_ID_2).build());


        recipeToRecipeCommand = RecipeToRecipeCommand.builder()
                .categoryToCategoryCommand(new CategoryToCategoryCommand())
                .ingredientToIngredientCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()))
                .notesToNotesCommand(new NotesToNotesCommand())
                .build();


        recipeCommand = recipeToRecipeCommand.convert(recipe1);

        mockMvc = MockMvcBuilders
                .standaloneSetup(recipeViewsControllerVictim)
                .setControllerAdvice(ControllerExceptionHandler.class)
                .build();

    }

    @Test
    void getRecipeTest() throws Exception {

        when(recipeServiceMock.getRecipeById(anyLong())).thenReturn(recipe1);

        mockMvc.perform(get("/recipe/1/show/"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void getRecipeTestRecipeNotFoundException() throws Exception {
        when(recipeServiceMock.getRecipeById(anyLong())).thenThrow(RecipeNotFoundException.class);
        mockMvc.perform(get("/recipe/1/show/"))
               .andExpect(status().isNotFound())
               .andExpect(view().name("errors/404Error"));
    }

    @Test
    void testNumberFormatException() throws Exception {
        mockMvc.perform(get("/recipe/a/show/"))
               .andExpect(status().isBadRequest())
               .andExpect(view().name("errors/400Error"));
    }

    @Test
    void testGetNewRecipeFrom() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testPostNewRecipeForm() throws Exception{
        when(recipeServiceMock.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id",RECIPE_ID.toString())
                        .param("description",RECIPE_DESCRIPTION)
                        .param("prepTime", RECIPE_PREP_TIME.toString())
                        .param("cookTime", RECIPE_COOK_TIME.toString())
                        .param("servings", RECIPE_SERVINGS.toString())
                        .param("url", RECIPE_URL)
                        .param("directions", RECIPE_DIRECTIONS)
                        .param("source", RECIPE_SOURCE)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(RECIPE_REDIRECT.getLink().concat("1/show/")));
    }


    @Test
    void testPostNewRecipeFormFail() throws Exception{
        var recipeCommandFailMock = RecipeCommand.builder().id(2L).build();

        when(recipeServiceMock.saveRecipeCommand(any(),  any())).thenReturn(recipeCommandFailMock);

        mockMvc.perform(post("/recipe")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("id", "")
        )
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("recipe"))
               .andExpect(view().name(RECIPE_FORM.getLink()));
    }

    @Test
    void testsGetUpdateView() throws Exception {

        when(recipeServiceMock.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE_FORM.getLink()))
                .andExpect(model().attributeExists("recipe"));
    }


    @Test
    void testDeleteAction() throws Exception{

        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(RECIPE_REDIRECT_ALL.getLink()));

        verify(recipeServiceMock, times(1)).deleteRecipeById(anyLong());
    }
}