package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import com.sbtraining.recipe_project.model.UnitOfMeasure;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 11/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {


    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

        if(unitOfMeasure != null)
            return UnitOfMeasureCommand.builder()
                    .id(unitOfMeasure.getId())
                    .description(unitOfMeasure.getDescription())
                    .build();

        return null;
    }
}
