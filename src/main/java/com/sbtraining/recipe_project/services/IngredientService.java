package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.exceptions.IngredientNotFoundException;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.exceptions.UnitOfMeasureNotFoundException;
import com.sbtraining.recipe_project.model.Ingredient;
import javassist.NotFoundException;

import java.util.List;

/**
 * Created by sousaJ on 21/11/2020
 * in package - com.sbtraining.recipe_project.services
 **/

public interface IngredientService {

    List<Ingredient> getAllIngredients();
    Ingredient getIngredientById(Long id) throws NotFoundException;

    void deleteIngredientById(Long id);

    void deleteByRecipeIdAndId(Long recipeId, Long id) throws RecipeNotFoundException, IngredientNotFoundException;

    List<Ingredient> findAllByRecipeId(Long recipeId);

    List<IngredientCommand> findAllRecipeIngredientsByRecipeId(Long recipeId);

    IngredientCommand saveIngredientCommand(IngredientCommand command) throws UnitOfMeasureNotFoundException, IngredientNotFoundException;
    IngredientCommand findIngredientCommandById(Long id) throws NotFoundException;
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) throws NotFoundException, RecipeNotFoundException;

}
