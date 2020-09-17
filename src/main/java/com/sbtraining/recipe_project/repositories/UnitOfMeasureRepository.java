package com.sbtraining.recipe_project.repositories;

import com.sbtraining.recipe_project.model.Category;
import com.sbtraining.recipe_project.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sousaJ on 17/09/2020
 * in package - com.sbtraining.recipe_project.repositories
 **/
@Repository
public interface UnitOfMeasureRepository extends PagingAndSortingRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
