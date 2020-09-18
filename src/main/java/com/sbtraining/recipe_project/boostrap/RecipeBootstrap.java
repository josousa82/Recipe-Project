package com.sbtraining.recipe_project.boostrap;

import com.sbtraining.recipe_project.model.Ingredient;
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
import java.util.Collection;
import java.util.Optional;
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


        Recipe recipe = new Recipe();
        recipe = addIngredientToRecipe(recipe, "Avocado", "Units", 2);
        recipe = addIngredientToRecipe(recipe, "Salt", "Teaspoon", 2);
        recipe = addIngredientToRecipe(recipe, "Lemon", "Millilitre", 5);

        recipeRepository.save(recipe);


        StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList())
                .forEach(x -> {
                    System.out.println("recipe1.toString() = " + x.getId().toString());

                });

        recipeIngredientRepository.getRecipeIngredientByRecipe_Id(1L).stream()
                .flatMap(Collection::parallelStream)
                .map(RecipeIngredient::getIngredient)
                .map(Ingredient::getDescription)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }


    private Recipe addIngredientToRecipe(Recipe recipe, String ingredientName, String unit, Integer quantity){

        RecipeIngredient ingredient = new RecipeIngredient();
        ingredient.setIngredient(ingredientRepository.findByDescription(ingredientName));
        ingredient.setQuantity(BigDecimal.valueOf(quantity));
        ingredient.setUom(unitOfMeasureRepository.findByDescription(unit).get());
        ingredient.setRecipe(recipe);
        recipe.getIngredients().add(ingredient);

        return recipe;
    }
}
