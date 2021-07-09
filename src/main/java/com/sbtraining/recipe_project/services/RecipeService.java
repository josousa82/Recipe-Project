package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.model.Recipe;
import javassist.NotFoundException;
import org.springframework.validation.BindingResult;

import java.util.Set;

/**
 * Created by sousaJ on 01/10/2020
 * in package - com.sbtraining.recipe_project.services
 **/
public interface RecipeService {

     Set<Recipe> getAllRecipes();

     Recipe getRecipeById(Long id) throws NotFoundException;

     Recipe saveRecipe(Recipe recipe);

     RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand, BindingResult bindingResult) ;

     RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) ;

     RecipeCommand findCommandById(Long id) throws NotFoundException;

     void deleteRecipeById(Long id);
}
