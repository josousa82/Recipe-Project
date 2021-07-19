package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.*;
import com.sbtraining.recipe_project.converters.RecipeCommandToRecipe;
import com.sbtraining.recipe_project.converters.RecipeToRecipeCommand;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.model.*;
import com.sbtraining.recipe_project.model.enums.Difficulty;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImpITTest {


    public static final Long RECIPE_ID = 1L;
    public static final String RECIPE_DESCRIPTION = "flower";
    public static final Integer RECIPE_PREP_TIME = 30;
    public static final Integer RECIPE_COOK_TIME = 15;
    public static final Integer RECIPE_SERVINGS = 4;
    public static final String RECIPE_SOURCE = "flower";
    public static final String RECIPE_URL = "flower";
    public static final String RECIPE_DIRECTIONS = "flower";
    public static final Difficulty RECIPE_DIFFICULTY = Difficulty.EASY;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGREDIENT_ID_1 = 1L;
    public static final Long INGREDIENT_ID_2 = 2L;
    public static final Long NOTES_ID_1 = 8L;

    RecipeCommand recipeCommand;

    Recipe recipe;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    String expectedMsg = "Recipe not found. For ID  with value: 1";

    @BeforeEach
    void setUp() {

        var notesCommand = NotesCommand.builder()
                .id(NOTES_ID_1)
                .build();

        recipeCommand = RecipeCommand.builder()
                .id(RECIPE_ID)
                .description(RECIPE_DESCRIPTION)
                .prepTime(RECIPE_PREP_TIME)
                .cookTime(RECIPE_COOK_TIME)
                .servings(RECIPE_SERVINGS)
                .source(RECIPE_SOURCE)
                .url(RECIPE_URL)
                .directions(RECIPE_DIRECTIONS)
                .difficulty(RECIPE_DIFFICULTY)
                .notes(notesCommand)
                .build();


        recipeCommand.addIngredientCommand(IngredientCommand.builder().id(INGREDIENT_ID_1)
                .uom(UnitOfMeasureCommand.builder().id(INGREDIENT_ID_1).build()).build());
        recipeCommand.addIngredientCommand(IngredientCommand.builder().id(INGREDIENT_ID_1)
                .uom(UnitOfMeasureCommand.builder().id(INGREDIENT_ID_2).build()).build());

        recipeCommand.addCategoryCommand(CategoryCommand.builder().id(CAT_ID_1).build());
        recipeCommand.addCategoryCommand(CategoryCommand.builder().id(CAT_ID_2).build());


        recipe = Recipe.builder()
                .id(RECIPE_ID)
                .description(RECIPE_DESCRIPTION)
                .prepTime(RECIPE_PREP_TIME)
                .cookTime(RECIPE_COOK_TIME)
                .servings(RECIPE_SERVINGS)
                .source(RECIPE_SOURCE)
                .url(RECIPE_URL)
                .directions(RECIPE_DIRECTIONS)
                .difficulty(RECIPE_DIFFICULTY)
                .build();

        var notes = Notes.builder()
                .id(NOTES_ID_1)
                .build();

        recipe.setNotes(notes);

        notes.setRecipe(recipe);

        recipe.addIngredient(Ingredient.builder().id(INGREDIENT_ID_1).uom(UnitOfMeasure.builder().id(INGREDIENT_ID_1).build()).build());
        recipe.addIngredient(Ingredient.builder().id(INGREDIENT_ID_2).uom(UnitOfMeasure.builder().id(INGREDIENT_ID_2).build()).build());

        recipe.addCategory(Category.builder().id(CAT_ID_1).build());
        recipe.addCategory(Category.builder().id(CAT_ID_2).build());

        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void testGetAllRecipes() {
        Recipe recipe1 = Recipe.builder().id(1L).build();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe1);
        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipeSet = recipeService.getAllRecipes();
        assertEquals(1, recipeSet.size());

        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void testGetRecipeById() throws RecipeNotFoundException {
        Recipe recipe1 = Recipe.builder().id(1L).build();
        HashSet<Recipe> recipeHashSet = new HashSet<>();
        recipeHashSet.add(recipe1);

        Optional<Recipe> recipeOptional = Optional.of(recipe1);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.getRecipeById(1L);

        assertNotNull(recipeReturned);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void getRecipeByIdThrowsExceptionIfIsNotFound()  {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(RecipeNotFoundException.class, () -> recipeService.getRecipeById(1L));
        assertTrue(expectedMsg.contains(exception.getMessage()));
    }

    @Test
    void testSaveRecipeCommand() throws RecipeNotFoundException {

        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(recipeCommandToRecipe.convert(recipeCommand)).thenReturn(recipe);
        when(recipeToRecipeCommand.convert(recipe)).thenReturn(recipeCommand);

        RecipeCommand savedRecipeCommand  = recipeService.saveRecipeCommand(recipeCommand, any());

        assertNotNull(savedRecipeCommand);
        assertEquals(RECIPE_ID, savedRecipeCommand.getId());
        assertEquals(RECIPE_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(RECIPE_PREP_TIME, savedRecipeCommand.getPrepTime());
        assertEquals(RECIPE_COOK_TIME, savedRecipeCommand.getCookTime());
        assertEquals(RECIPE_SERVINGS, savedRecipeCommand.getServings());
        assertEquals(RECIPE_SOURCE, savedRecipeCommand.getSource());
        assertEquals(RECIPE_URL, savedRecipeCommand.getUrl());
        assertEquals(RECIPE_DIRECTIONS, savedRecipeCommand.getDirections());
        assertEquals(RECIPE_DIFFICULTY, savedRecipeCommand.getDifficulty());
        assertEquals(NOTES_ID_1, savedRecipeCommand.getNotes().getId());
        assertEquals(2, savedRecipeCommand.getCategories().size());
        assertEquals(2, savedRecipeCommand.getIngredients().size());

    }

    @Test
    void testDeleteRecipeById() {
        recipeService.deleteRecipeById(2L);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testSaveRecipe() {
    }

    @Test
    void testFindCommandById() {
    }

}