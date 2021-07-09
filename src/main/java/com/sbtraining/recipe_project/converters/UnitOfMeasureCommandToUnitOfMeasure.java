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
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {

        if(unitOfMeasureCommand != null){
            return UnitOfMeasure.builder()
                    .id(unitOfMeasureCommand.getId())
                    .description(unitOfMeasureCommand.getDescription())
                    .build();
        }
        return null;
    }
}
