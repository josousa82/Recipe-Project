package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.CategoryCommand;
import com.sbtraining.recipe_project.model.Category;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 09/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {

        if (category == null) return null;

        final var categoryCommand = CategoryCommand.builder()
                .id(category.getId())
                .description(category.getDescription())
                .build();

        return categoryCommand;
    }
}
