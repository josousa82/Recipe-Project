package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.ImageCommand;
import com.sbtraining.recipe_project.model.ImageModel;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.module.ResolutionException;

/**
 * Created by sousaJ on 03/01/2021
 * in package - com.sbtraining.recipe_project.converters
 **/
@Slf4j
@Component
@Builder
public class ImageCommandToImage implements Converter<ImageCommand, ImageModel> {

    private final RecipeRepository recipeRepository;

    @Override
    public ImageModel convert(ImageCommand imageCommand) {
        if(imageCommand == null) return null;

        Long recipeId = imageCommand.getRecipeId();
        return ImageModel.builder()
                         .id(imageCommand.getId())
                         .recipe(recipeRepository.findById(recipeId).orElseThrow(() -> {
                             log.error("ImageCommandToImage : Recipe with id {} not found.", recipeId);
                             return new ResolutionException("Recipe not found exception.");
                         }))
                         .imageBytes(imageCommand.getImageBytes())
                         .name(imageCommand.getName())
                         .type(imageCommand.getType())
                         .build();
    }
}
