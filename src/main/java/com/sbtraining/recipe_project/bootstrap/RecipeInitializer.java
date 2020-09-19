package com.sbtraining.recipe_project.bootstrap;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.model.UnitOfMeasure;
import com.sbtraining.recipe_project.repositories.CategoryRepository;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sousaJ on 19/09/2020
 * in package - com.sbtraining.recipe_project.bootstrap
 **/

@Component
public class RecipeInitializer  {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public RecipeInitializer(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);
        // get UOM
        Optional<UnitOfMeasure> tableSpoonUOM = unitOfMeasureRepository.findByDescription("TableSpoon");
        checkOptional(tableSpoonUOM);

        Optional<UnitOfMeasure> teaSpoonUOM = unitOfMeasureRepository.findByDescription("Teaspoon");
        checkOptional(teaSpoonUOM);

        Optional<UnitOfMeasure> dashSpoonUOM = unitOfMeasureRepository.findByDescription("Dash");
        checkOptional(dashSpoonUOM);

        Optional<UnitOfMeasure> pintSpoonUOM = unitOfMeasureRepository.findByDescription("Pint");
        checkOptional(pintSpoonUOM);

        Optional<UnitOfMeasure> cupSpoonUOM = unitOfMeasureRepository.findByDescription("Cup");
        checkOptional(cupSpoonUOM);


        return null;

    }

    private void checkOptional(Optional<UnitOfMeasure> uomOptional) {
        if (uomOptional.isEmpty()) {
            throw new RuntimeException("Uom not found");
        }
    }
}
