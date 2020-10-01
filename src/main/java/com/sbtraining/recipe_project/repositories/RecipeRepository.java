package com.sbtraining.recipe_project.repositories;

import com.sbtraining.recipe_project.model.Recipe;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by sousaJ on 17/09/2020
 * in package - com.sbtraining.recipe_project.repositories
 **/
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long> {
}
