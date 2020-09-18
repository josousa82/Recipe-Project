package com.sbtraining.recipe_project.repositories;

import com.sbtraining.recipe_project.model.RecipeIngredient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sousaJ on 18/09/2020
 * in package - com.sbtraining.recipe_project.repositories
 **/
@Repository
public interface RecipeIngredientRepository extends PagingAndSortingRepository<RecipeIngredient, Long> {

    Optional<RecipeIngredient> getRecipeIngredientByRecipe_Id(Long id);
}
