package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.model.Recipe;
import com.sun.istack.Nullable;
import lombok.Builder;
import lombok.Synchronized;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by sousaJ on 12/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/
@Component
@Builder
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 NotesCommandToNotes notesCommandToNotes) {

        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {

        if(recipeCommand == null) return null;

         final Recipe recipe = Recipe.builder()
                .id(recipeCommand.getId())
                .image(recipeCommand.getImage())
                .description(recipeCommand.getDescription())
                .prepTime(recipeCommand.getPrepTime())
                .cookTime(recipeCommand.getCookTime())
                .servings(recipeCommand.getServings())
                .source(recipeCommand.getSource())
                .url(recipeCommand.getUrl())
                .directions(recipeCommand.getDirections())
                .difficulty(recipeCommand.getDifficulty())
                .notes(notesCommandToNotes.convert(recipeCommand.getNotes())).build();

        if (CollectionUtils.isNotEmpty(recipeCommand.getIngredients()))
        {
            recipeCommand.getIngredients().forEach(ingredientCommand ->
                    recipe.addIngredient(Objects.requireNonNull(ingredientCommandToIngredient.convert(ingredientCommand))));
        }

        if (CollectionUtils.isNotEmpty(recipeCommand.getCategories()))
        {
            recipeCommand.getCategories().forEach(categoryCommand ->
                    recipe.addCategory(Objects.requireNonNull(categoryCommandToCategory.convert(categoryCommand))) );
        }

        return recipe;
    }
}
