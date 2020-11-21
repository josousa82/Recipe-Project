package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.IngredientCommand;
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

    IngredientCommand saveIngredientCommand(IngredientCommand command);
    IngredientCommand findIngredientCommandById(Long id) throws NotFoundException;
}
