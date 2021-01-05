package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.ImageCommand;
import com.sbtraining.recipe_project.model.ImageModel;
import com.sun.istack.Nullable;
import lombok.Builder;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 03/01/2021
 * in package - com.sbtraining.recipe_project.converters
 **/
@Component
@Builder
public class ImageToImageCommand implements Converter<ImageModel, ImageCommand> {


    @Synchronized
    @Nullable
    @Override
    public ImageCommand convert(ImageModel imageModel) {
        return ImageCommand.builder()
                .id(imageModel.getId())
                .recipeId(imageModel.getRecipe().getId())
                .imageBytes(imageModel.getImageBytes())
                .name(imageModel.getName())
                .type(imageModel.getType())
                .build();

    }
}
