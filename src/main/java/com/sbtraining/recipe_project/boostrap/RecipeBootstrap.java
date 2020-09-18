package com.sbtraining.recipe_project.boostrap;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.model.RecipeIngredient;
import com.sbtraining.recipe_project.repositories.IngredientRepository;
import com.sbtraining.recipe_project.repositories.RecipeIngredientRepository;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import com.sbtraining.recipe_project.services.RecipeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by sousaJ on 18/09/2020
 * in package - com.sbtraining.recipe_project.boostrap
 **/

@Component
public class RecipeBootstrap implements CommandLineRunner {

    private final RecipeService recipeService;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public RecipeBootstrap(RecipeService recipeService, RecipeIngredientRepository recipeIngredientRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeService = recipeService;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        recipeService.createNewRecipe(createRecipe());
        var recipe = new Recipe();
        var recipe1 = new Recipe();
        var recipe2 = new Recipe();
        var recipe3 = new Recipe();
        var recipe4 = new Recipe();
        var recipe5 = new Recipe();

        recipeRepository.save(recipe);
        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);
        recipeRepository.save(recipe4);
        recipeRepository.save(recipe5);

        StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList())
                .forEach(x -> {
                    System.out.println("recipe1.toString() = " + x.getId().toString());

                });
    }


    private Recipe createRecipe(Recipe recipe, String ingredientName, String unit, Integer quantity ){

        RecipeIngredient ingredient = new RecipeIngredient();

        ingredient.setIngredient(ingredientRepository.findByDescription(ingredientName));

        ingredient.setQuantity(BigDecimal.valueOf(quantity));
        ingredient.setUom(unitOfMeasureRepository.findByDescription(unit).get());

        Set<RecipeIngredient> ingredientsRecipe = new HashSet<>();
        ingredientsRecipe.add(ingredient);

        recipe.setIngredients(ingredientsRecipe);

        return recipe;
    }
}
