package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.model.Ingredient;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.model.RecipeIngredient;
import com.sbtraining.recipe_project.repositories.RecipeIngredientRepository;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by sousaJ on 18/09/2020
 * in package - com.sbtraining.recipe_project.services
 **/
@Slf4j
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeService(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public Recipe createNewRecipe(Recipe recipe){
        if(Objects.isNull(recipe)) {
            log.error("Recipe cannot by null");
            throw new IllegalArgumentException("Recipe cannot by null");
        }
        else {
            recipeRepository.save(recipe);
            return recipe;
        }
    }

    public List<Ingredient> getRecipeIngredients(Recipe recipe){
        return StreamSupport.stream(recipeIngredientRepository.getRecipeIngredientByRecipe_Id(
                recipe.getId()).stream().spliterator(), false)
                .flatMap(Collection::parallelStream)
                .map(RecipeIngredient::getIngredient)
                .collect(Collectors.toList());
    }


}
