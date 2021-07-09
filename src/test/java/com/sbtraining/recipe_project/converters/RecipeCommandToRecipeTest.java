package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.*;
import com.sbtraining.recipe_project.model.enums.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

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

    RecipeCommand recipeCommandToConvert;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {

        var notesCommand = NotesCommand.builder()
                .id(NOTES_ID_1)
                .build();

        recipeCommandToConvert = RecipeCommand.builder()
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


        recipeCommandToConvert.addIngredientCommand(IngredientCommand.builder().id(INGREDIENT_ID_1)
                .uom(UnitOfMeasureCommand.builder().id(INGREDIENT_ID_1).build()).build());
        recipeCommandToConvert.addIngredientCommand(IngredientCommand.builder().id(INGREDIENT_ID_1)
                .uom(UnitOfMeasureCommand.builder().id(INGREDIENT_ID_2).build()).build());

        recipeCommandToConvert.addCategoryCommand(CategoryCommand.builder().id(CAT_ID_1).build());
        recipeCommandToConvert.addCategoryCommand(CategoryCommand.builder().id(CAT_ID_2).build());

        converter = RecipeCommandToRecipe.builder()
                .categoryCommandToCategory(new CategoryCommandToCategory())
                .ingredientCommandToIngredient(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()))
                .notesCommandToNotes(new NotesCommandToNotes())
                .build();

    }

    @Test
    void testForNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void testConvertRecipeToRecipeCommand() {

        // when

        var recipe = converter.convert(recipeCommandToConvert);

        // then

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(RECIPE_DESCRIPTION, recipe.getDescription());
        assertEquals(RECIPE_PREP_TIME, recipe.getPrepTime());
        assertEquals(RECIPE_COOK_TIME, recipe.getCookTime());
        assertEquals(RECIPE_SERVINGS, recipe.getServings());
        assertEquals(RECIPE_SOURCE, recipe.getSource());
        assertEquals(RECIPE_URL, recipe.getUrl());
        assertEquals(RECIPE_DIRECTIONS, recipe.getDirections());
        assertEquals(RECIPE_DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID_1, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());

    }
}