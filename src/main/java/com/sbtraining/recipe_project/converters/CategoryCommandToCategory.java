package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.CategoryCommand;
import com.sbtraining.recipe_project.model.Category;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;


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
            if(Objects.isNull(command)){
                return null;
            }
            final  Category category = Category.builder()
                    .id(command.getId())
                    .description(command.getDescription())
                    .build();
        return category ;
    }
}
