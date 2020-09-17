package com.sbtraining.recipe_project.repositories;

import com.sbtraining.recipe_project.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sousaJ on 17/09/2020
 * in package - com.sbtraining.recipe_project.repositories
 **/
@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
