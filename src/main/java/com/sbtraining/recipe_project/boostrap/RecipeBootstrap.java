package com.sbtraining.recipe_project.boostrap;

import com.sbtraining.recipe_project.model.*;
import com.sbtraining.recipe_project.model.enums.Difficulty;
import com.sbtraining.recipe_project.repositories.*;
import com.sbtraining.recipe_project.services.RecipeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
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
    private final CategoryRepository categoryRepository;


    public RecipeBootstrap(RecipeService recipeService, RecipeIngredientRepository recipeIngredientRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeService = recipeService;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Recipe guacaRecipe = getGuacaRecipe();

        addIngredientToRecipe(guacaRecipe, "Avocado", "Units", 2.0);
        addIngredientToRecipe(guacaRecipe, "Salt", "Teaspoon", 0.25);
        addIngredientToRecipe(guacaRecipe, "Lemon", "Millilitre", 2.0);
        addIngredientToRecipe(guacaRecipe, "Chiles", "Units", 2.0);
        addIngredientToRecipe(guacaRecipe, "Onion", "Cup", 0.25);
        addIngredientToRecipe(guacaRecipe, "Cilantro", "Tablespoon", 2.0);
        addIngredientToRecipe(guacaRecipe, "Black Pepper", "Units", 1.0);
        addIngredientToRecipe(guacaRecipe, "Tomato", "Units", 2.0);


        recipeRepository.save(guacaRecipe);


        StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList())
                .forEach(x -> {
                    System.out.println("recipe1.toString() = " + x.getId().toString());

                });

        recipeIngredientRepository.getRecipeIngredientByRecipe_Id(1L).stream()
                .flatMap(Collection::parallelStream)
                .map(RecipeIngredient::getIngredient)
                .map(Ingredient::getDescription)
                .forEach(System.out::println);
    }


    private Recipe getGuacaRecipe() {
        Recipe guacaRecipe = new Recipe();
        guacaRecipe.setDescription("Guacamole Recipe");
        guacaRecipe.setPrepTime(10);
        guacaRecipe.setCookTime(0);
        guacaRecipe.setServings(4);
        guacaRecipe.setDifficulty(Difficulty.EASY);

        guacaRecipe.setSource("simplyrecipes");
        guacaRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacaRecipe.setDirections("1 Cut the avocado, remove flesh: " + "\n" +
                        "Cut the avocados in half. Remove the pit. " + "\n" +
                        "Score the inside of the avocado with a blunt " + "\n" +
                        "knife and scoop out the flesh with a spoon. " + "\n" +
                        "2 Mash with a fork: Using a fork, roughly mash the avocado. " + "\n" +
                        "(Don't overdo it! The guacamole should be a little chunky.)." + "\n" +
                        "3 Add salt, lime juice, and the rest: Sprinkle with salt and " + "\n" +
                        "lime (or lemon) juice. The acid in the lime juice will provide " + "\n" +
                        "some balance to the richness of the avocado and will help delay " + "\n" +
                        "the avocados from turning brown. Add the chopped onion, cilantro," + "\n" +
                        "black pepper, and chiles. Chili peppers vary individually in " + "\n" +
                        "their hotness. So, start with a half of one chili pepper and add " + "\n" +
                        "to the guacamole to your desired degree of hotness. Remember that " + "\n" +
                        "much of this is done to taste because of the variability in the fresh " + "\n" +
                        "ingredients. Start with this guacaRecipe and adjust to your taste. Chilling " + "\n" +
                        "tomatoes hurts their flavor, so if you want to add chopped tomato to " + "\n" +
                        "your guacamole, add it just before serving. 4 Serve: Serve immediately, " + "\n" +
                        "or if making a few hours ahead, place plastic wrap on the surface of " + "\n" +
                        "the guacamole and press down to cover it and to prevent air reaching it. " + "\n" +
                        "The oxygen in the air causes oxidation which will turn the guacamole brown. " + "\n" +
                        "Refrigerate until ready to serve.");
        Notes guacaNotes = new Notes();
        guacaNotes.setNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling" +
                "and do not touch your eyes or the area near your eyes with your hands for several hours.");
        guacaRecipe.setNotes(guacaNotes);

        guacaRecipe.getCategories().add(categoryRepository.findByCategoryName("Mexican").get());
        return guacaRecipe;
    }


    private void addIngredientToRecipe(Recipe recipe, String ingredientName, String unit, Double quantity){

        RecipeIngredient ingredient = new RecipeIngredient();
        ingredient.setIngredient(ingredientRepository.findByDescription(ingredientName));
        ingredient.setQuantity(BigDecimal.valueOf(quantity));
        ingredient.setUom(unitOfMeasureRepository.findByDescription(unit).get());
        ingredient.setRecipe(recipe);
        recipe.getIngredients().add(ingredient);
    }
}
