package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.model.Ingredient;
import com.sun.istack.Nullable;
import lombok.Builder;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 09/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/
@Builder
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

   private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {

        if(ingredient == null) return null;

        var ingredientCommand =  IngredientCommand.builder()
                .id(ingredient.getId())
                .amount(ingredient.getAmount())
                .description(ingredient.getDescription())
                .uomc(uomConverter.convert(ingredient.getUom()))
                .build();

        if (ingredient.getRecipe() != null) ingredientCommand.setRecipeId(ingredient.getId());

        return ingredientCommand;
    }
}
