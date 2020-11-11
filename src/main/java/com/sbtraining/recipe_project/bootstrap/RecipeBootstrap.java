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
        recipeRepository.saveAll(getRecipesBoostrapData());
    }

    private List<Recipe> getRecipesBoostrapData() {

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

        guacamoleRecipe.addIngredient(Ingredient.builder().description("ripe avocados").amount(new BigDecimal(2)).uom(eachUom).build())
                .addIngredient(Ingredient.builder().description("Kosher salt").amount(new BigDecimal(".5")).uom(teaspoon).build())
                .addIngredient(Ingredient.builder().description("fresh lime juice or lemon juice").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .addIngredient(Ingredient.builder().description("minced red onion or thinly sliced green onion").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .addIngredient(Ingredient.builder().description("serrano chiles, stems and seeds removed, minced").amount(new BigDecimal(2)).uom(eachUom).build())
                .addIngredient(Ingredient.builder().description("Cilantro").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .addIngredient(Ingredient.builder().description("freshly grated black pepper").amount(new BigDecimal(2)).uom(dashUom).build())
                .addIngredient(Ingredient.builder().description("ripe tomato, seeds and pulp removed, chopped").amount(new BigDecimal(".5")).uom(eachUom).build());

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

        tacosRecipe.addIngredient(Ingredient.builder().description("Ancho Chili Powder").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .addIngredient(Ingredient.builder().description("Dried Oregano").amount(new BigDecimal(1)).uom(teaspoon).build())
                .addIngredient(Ingredient.builder().description("Dried Cumin").amount(new BigDecimal(1)).uom(teaspoon).build())
                .addIngredient(Ingredient.builder().description("Sugar").amount( new BigDecimal(1)).uom( teaspoon).build())
                .addIngredient(Ingredient.builder().description("Salt").amount(new BigDecimal(".5")).uom(teaspoon).build())
                .addIngredient(Ingredient.builder().description("Clove of Garlic, Choppedr").amount(new BigDecimal("1")).uom(eachUom).build())
                .addIngredient(Ingredient.builder().description("finely grated orange zestr").amount(new BigDecimal(1)).uom(tableSpoonUom).build())
                .addIngredient(Ingredient.builder().description("fresh-squeezed orange juice").amount(new BigDecimal(3)).uom(tableSpoonUom).build())
                .addIngredient(Ingredient.builder().description("Olive Oil").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .addIngredient(Ingredient.builder().description("boneless chicken thighs").amount(new BigDecimal(4)).uom(tableSpoonUom).build())
                .addIngredient(Ingredient.builder().description("small corn tortillasr").amount(new BigDecimal(8)).uom(eachUom).build())
                .addIngredient(Ingredient.builder().description("packed baby arugula").amount(new BigDecimal(3)).uom(cupsUom).build())
                .addIngredient(Ingredient.builder().description("medium ripe avocados, slice").amount(new BigDecimal(2)).uom(eachUom).build())
                .addIngredient(Ingredient.builder().description("radishes, thinly sliced").amount(new BigDecimal(4)).uom(eachUom).build())
                .addIngredient(Ingredient.builder().description("cherry tomatoes, halved").amount(new BigDecimal(".5")).uom(pintUom).build())
                .addIngredient(Ingredient.builder().description("red onion, thinly sliced").amount(new BigDecimal(".25")).uom(eachUom).build())
                .addIngredient(Ingredient.builder().description("Roughly chopped cilantro").amount(new BigDecimal(4)).uom(eachUom).build())
                .addIngredient(Ingredient.builder().description("cup sour cream thinned with 1/4 cup milk").amount(new BigDecimal(4)).uom(cupsUom).build())
                .addIngredient(Ingredient.builder().description("lime, cut into wedges").amount(new BigDecimal(4)).uom(eachUom).build());

        recipes.add(tacosRecipe);

        return recipes;
    }
}
