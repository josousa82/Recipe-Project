package com.sbtraining.recipe_project.repositories;

import com.sbtraining.recipe_project.model.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sousaJ on 21/11/2020
 * in package - com.sbtraining.recipe_project.repositories
 **/
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    @Override
    Iterable<Ingredient> findAll();

    @Override
    Optional<Ingredient> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Query
    Ingredient findByRecipeIdAndId(Long recipeId, Long ingredientId);

}
