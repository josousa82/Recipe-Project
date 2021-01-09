package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.ImageCommand;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sousaJ on 02/01/2021
 * in package - com.sbtraining.recipe_project.services
 **/

public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile any);
    ImageCommand saveImageCommand(ImageCommand imageCommand);
    ImageCommand findImageCommandById(Long id);
}
