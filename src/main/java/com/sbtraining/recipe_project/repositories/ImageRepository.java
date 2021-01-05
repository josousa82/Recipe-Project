package com.sbtraining.recipe_project.repositories;

import com.sbtraining.recipe_project.model.ImageModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sousaJ on 03/01/2021
 * in package - com.sbtraining.recipe_project.repositories
 **/
@Repository
public interface ImageRepository extends PagingAndSortingRepository<ImageModel, Long>{
    Optional<ImageModel> findByRecipeIdAndId(Long recipeId, Long imageId);
}
