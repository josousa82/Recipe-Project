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
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand command) {

        if(command == null) return null;


//        Objects.requireNonNull(command);

        return Category.builder()
                .id(command.getId())
                .description(command.getDescription())
                .build();
    }
}
