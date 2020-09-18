package com.sbtraining.recipe_project.repositories;

import com.sbtraining.recipe_project.model.Ingredient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sousaJ on 18/09/2020
 * in package - com.sbtraining.recipe_project.repositories
 **/
@Repository
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long> {

    Ingredient findByDescription(String description);

}
