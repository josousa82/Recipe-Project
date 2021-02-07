package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.model.Category;
import com.sbtraining.recipe_project.repositories.CategoryRepository;
import com.sbtraining.recipe_project.utils.Filters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by sousaJ on 07/02/2021
 * in package - com.sbtraining.recipe_project.services
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Set<String> getAllDistinctCategoriesDescription(){
        return StreamSupport
                .stream(categoryRepository.findAll().spliterator(), false)
                .filter(Filters.distinctKey(Category::getDescription))
                .map(Category::getDescription)
                .collect(Collectors.toSet());
    }

}
