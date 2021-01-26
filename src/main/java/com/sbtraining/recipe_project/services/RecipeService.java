package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.model.Recipe;
import javassist.NotFoundException;

import java.util.Set;

/**
 * Created by sousaJ on 01/10/2020
 * in package - com.sbtraining.recipe_project.services
 **/
public interface RecipeService {

     Set<Recipe> getAllRecipes();

     Recipe getRecipeById(Long id) throws NotFoundException, RecipeNotFoundException;

     Recipe saveRecipe(Recipe recipe);

     RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) throws RecipeNotFoundException;

     RecipeCommand findCommandById(Long id) throws NotFoundException, RecipeNotFoundException;

     void deleteRecipeById(Long id);
}
