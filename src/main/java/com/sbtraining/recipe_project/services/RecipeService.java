package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.model.Recipe;

import java.util.Set;

/**
 * Created by sousaJ on 01/10/2020
 * in package - com.sbtraining.recipe_project.services
 **/
public interface RecipeService {
    public Set<Recipe> getRecipes();
}
