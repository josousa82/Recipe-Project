package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.model.Ingredient;
import com.sbtraining.recipe_project.model.Recipe;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 09/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/
@Builder
@AllArgsConstructor
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;


    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {

        if(ingredientCommand == null) return null;

        final var ingredientConverted = Ingredient.builder()
                .id(ingredientCommand.getId())
                .id(ingredientCommand.getId())
                .description(ingredientCommand.getDescription())
                .uom(uomConverter.convert(ingredientCommand.getUom()))
                .amount(ingredientCommand.getAmount())
                .build();

        // review this code, should take a recipe command to recipe converter

        if(ingredientCommand.getRecipeId() != null){
            var recipe = Recipe.builder()
                    .id(ingredientCommand.getRecipeId())
                    .build();
            ingredientConverted.setRecipe(recipe);
            recipe.addIngredient(ingredientConverted);
        }

        return ingredientConverted;
    }
}
