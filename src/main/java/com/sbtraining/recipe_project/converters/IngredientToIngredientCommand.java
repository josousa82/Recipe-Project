package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 09/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/
@Builder
@AllArgsConstructor
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

   private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {

        // review this null check  and solve it in other way
        if(ingredient == null) return null;

        IngredientCommand ingredientCommand =  IngredientCommand.builder()
                .id(ingredient.getId())
                .amount(ingredient.getAmount())
                .description(ingredient.getDescription())
                .uom(uomConverter.convert(ingredient.getUom()))
                .build();

        if (ingredient.getRecipe() != null) ingredientCommand.setRecipeId(ingredient.getRecipe().getId());

        return ingredientCommand;
    }
}
