package com.sbtraining.recipe_project.utils


import com.sbtraining.recipe_project.model.Recipe
import com.sbtraining.recipe_project.model.enums.Difficulty

class TestRecipeUtils {
    public static final Long RECIPE_ID = 1L;
    public static final String RECIPE_DESCRIPTION = "flower";
    public static final Integer RECIPE_PREP_TIME = 30;
    public static final Integer RECIPE_COOK_TIME = 15;
    public static final Integer RECIPE_SERVINGS = 4;
    public static final String RECIPE_SOURCE = "flower";
    public static final String RECIPE_URL = "http://www.myrecipe.com";
    public static final String RECIPE_DIRECTIONS = "flower";
    public static final Difficulty RECIPE_DIFFICULTY = Difficulty.EASY;

    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGREDIENT_ID_1 = 1L;
    public static final Long INGREDIENT_ID_2 = 2L;
    public static final Long NOTES_ID_1 = 8L;

    static recipe1 = new Recipe(
            id: RECIPE_ID,
            description: RECIPE_DESCRIPTION,
            directions: RECIPE_DIRECTIONS,
            difficulty: RECIPE_DIFFICULTY,
            prepTime: RECIPE_PREP_TIME,
            cookTime: RECIPE_COOK_TIME,
            servings: RECIPE_SERVINGS,
            source: RECIPE_SOURCE,
            url: RECIPE_URL,
            categories: CategoryUtils.setOfCategories,
            ingredients: TestIngredientUtils.listIngredients,
            notes: TestNotesUtils.notes_1
    )

    static recipe2 = new Recipe(
            id: 2,
            description: RECIPE_DESCRIPTION,
            directions: RECIPE_DIRECTIONS,
            difficulty: RECIPE_DIFFICULTY,
            prepTime: RECIPE_PREP_TIME,
            cookTime: RECIPE_COOK_TIME,
            servings: RECIPE_SERVINGS,
            source: RECIPE_SOURCE,
            url: RECIPE_URL,
            categories: CategoryUtils.setOfCategories,
            ingredients: TestIngredientUtils.listIngredients,
            notes: TestNotesUtils.notes_2
    )
}
