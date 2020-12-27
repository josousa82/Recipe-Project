package com.sbtraining.recipe_project.repositories;

import com.sbtraining.recipe_project.model.Ingredient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by sousaJ on 21/11/2020
 * in package - com.sbtraining.recipe_project.repositories
 **/
@Repository
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long> {

    @Override
    List<Ingredient> findAll(Sort sort);

    @Override
    Optional<Ingredient> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    void deleteByRecipeIdAndId(Long recipeId, Long id);

    @Query
    Ingredient findByRecipeIdAndId(Long recipeId, Long ingredientId);

    Iterable<Ingredient> findAllByRecipeId(Long recipeId, Sort sort);

}
