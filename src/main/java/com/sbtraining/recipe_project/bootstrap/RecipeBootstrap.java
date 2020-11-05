package com.sbtraining.recipe_project.bootstrap;

import com.sbtraining.recipe_project.model.*;
import com.sbtraining.recipe_project.model.enums.Difficulty;
import com.sbtraining.recipe_project.repositories.CategoryRepository;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sousaJ on 19/09/2020
 * in package - com.sbtraining.recipe_project.bootstrap
 **/
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final Environment environment;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, Environment environment) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        UnitOfMeasure eachUom = Optional.ofNullable(unitOfMeasureRepository.findByDescription("Each")).orElseThrow(IllegalArgumentException::new).get();
        UnitOfMeasure tableSpoonUom = Optional.ofNullable(unitOfMeasureRepository.findByDescription("Tablespoon")).orElseThrow(IllegalArgumentException::new).get();
        UnitOfMeasure teaspoon = Optional.ofNullable(unitOfMeasureRepository.findByDescription("Teaspoon")).orElseThrow(IllegalArgumentException::new).get();
        UnitOfMeasure dashUom = Optional.ofNullable(unitOfMeasureRepository.findByDescription("Dash")).orElseThrow(IllegalArgumentException::new).get();
        UnitOfMeasure pintUom = Optional.ofNullable(unitOfMeasureRepository.findByDescription("Pint")).orElseThrow(IllegalArgumentException::new).get();
        UnitOfMeasure cupsUom = Optional.ofNullable(unitOfMeasureRepository.findByDescription("Cup")).orElseThrow(IllegalArgumentException::new).get();

        //get Categories
        Category americanCategory = Optional.ofNullable(categoryRepository.findByDescription("American")).orElseThrow(IllegalArgumentException::new).get();
        Category mexicanCategory = Optional.ofNullable(categoryRepository.findByDescription("Mexican")).orElseThrow(IllegalArgumentException::new).get();

        //Yummy Guacamole
        Recipe guacamoleRecipe = Recipe.builder()
                .description("Perfect Guacamole")
                .prepTime(10)
                .cookTime(0)
                .servings(4)
                .difficulty(Difficulty.EASY)
                .directions(environment.getProperty("data.recipe.guacamole.directions"))
                .source("Simply Recipes")
                .url("http://www.simplyrecipes.com/recipes/perfect_guacamole")
                .build();

        guacamoleRecipe.addCategory(americanCategory)
                       .addCategory(mexicanCategory);

        Notes guacamoleNotes = Notes.builder()
                .recipeNotes(environment.getProperty("data.recipe.guacamole.notes"))
                .recipe(guacamoleRecipe)
                .build();

        guacamoleRecipe.setNotes(guacamoleNotes);

        recipes.add(guacamoleRecipe);

        guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoon))
                .addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom))
                .addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        //Yummy Tacos
        Recipe tacosRecipe = Recipe.builder()
                .description("Spicy Grilled Chicken Taco")
                .cookTime(9)
                .prepTime(20)
                .difficulty(Difficulty.MODERATE)
                .directions(environment.getProperty("data.recipe.tacos.directions"))
                .url("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/")
                .source("Simply Recipes")
                .servings(6)
                .build();

        Notes tacoNotes = Notes.builder()
                .recipeNotes(environment.getProperty("data.recipe.tacos.notes"))
                .recipe(tacosRecipe)
                .build();

        tacosRecipe.setNotes(tacoNotes);
        tacosRecipe.addCategory(mexicanCategory);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoon))
                .addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoon))
                .addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoon))
                .addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaspoon))
                .addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom))
                .addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom))
                .addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom))
                .addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom, tacosRecipe))
                .addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom, tacosRecipe))
                .addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom))
                .addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom))
                .addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom))
                .addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom))
                .addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom))
                .addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom))
                .addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom))
                .addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom))
                .addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));

        recipes.add(tacosRecipe);

        return recipes;
    }
}
