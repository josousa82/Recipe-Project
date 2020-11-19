package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.model.Recipe;
import com.sun.istack.Nullable;
import lombok.Builder;
import lombok.Synchronized;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 12/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/
@Component
@Builder
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand, IngredientToIngredientCommand ingredientToIngredientCommand, NotesToNotesCommand notesToNotesCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if(recipe == null) return null;

       final RecipeCommand recipeCommand = RecipeCommand.builder()
                .id(recipe.getId())
                .description(recipe.getDescription())
                .prepTime(recipe.getPrepTime())
                .cookTime(recipe.getCookTime())
                .servings(recipe.getServings())
                .source(recipe.getSource())
                .url(recipe.getUrl())
                .directions(recipe.getDirections())
                .difficulty(recipe.getDifficulty())
                .notes(notesToNotesCommand.convert(recipe.getNotes()))
                .build();

        if(CollectionUtils.isNotEmpty(recipe.getIngredients())){
            recipe.getIngredients().
                    forEach(ingredient -> recipeCommand.addIngredientCommand(ingredientToIngredientCommand.convert(ingredient)));
        }

        if(CollectionUtils.isNotEmpty(recipe.getCategories())){
            recipe.getCategories()
                    .forEach(category -> recipeCommand.addCategoryCommand(categoryToCategoryCommand.convert(category)));
        }

        return recipeCommand;
    }
}
