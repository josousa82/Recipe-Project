package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.model.*;
import com.sbtraining.recipe_project.model.enums.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

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

    Recipe recipeToConvert;

    RecipeToRecipeCommand converter;


    @BeforeEach
    void setUp() {

        recipeToConvert = Recipe.builder()
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

        recipeToConvert.setNotes(notes);

        notes.setRecipe(recipeToConvert);

        recipeToConvert.addIngredient(Ingredient.builder().id(INGREDIENT_ID_1).uom(UnitOfMeasure.builder().id(INGREDIENT_ID_1).build()).build());
        recipeToConvert.addIngredient(Ingredient.builder().id(INGREDIENT_ID_2).uom(UnitOfMeasure.builder().id(INGREDIENT_ID_2).build()).build());

        recipeToConvert.addCategory(Category.builder().id(CAT_ID_1).build());
        recipeToConvert.addCategory(Category.builder().id(CAT_ID_2).build());

        converter = RecipeToRecipeCommand.builder()
                .categoryToCategoryCommand(new CategoryToCategoryCommand())
                .ingredientToIngredientCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()))
                .notesToNotesCommand(new NotesToNotesCommand())
                .build();
    }

    @Test
    void testForNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void testConvertRecipeToRecipeCommand() {

        // when

        var command = converter.convert(recipeToConvert);

        // then

        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(RECIPE_DESCRIPTION, command.getDescription());
        assertEquals(RECIPE_PREP_TIME, command.getPrepTime());
        assertEquals(RECIPE_COOK_TIME, command.getCookTime());
        assertEquals(RECIPE_SERVINGS, command.getServings());
        assertEquals(RECIPE_SOURCE, command.getSource());
        assertEquals(RECIPE_URL, command.getUrl());
        assertEquals(RECIPE_DIRECTIONS, command.getDirections());
        assertEquals(RECIPE_DIFFICULTY, command.getDifficulty());
        assertEquals(NOTES_ID_1, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());

    }
}