package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by sousaJ on 18/09/2020
 * in package - com.sbtraining.recipe_project.services
 **/
@Slf4j
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createNewRecipe(Recipe recipe){
        if(Objects.isNull(recipe) || Objects.isNull(recipe.getId())) {
            log.error("Recipe cannot by null");
            throw new IllegalArgumentException("Recipe cannot by null");
        }
        else {
            recipeRepository.save(recipe);
            return recipe;
        }
    }
}
