package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 12/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {


    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        return null;
    }
}
