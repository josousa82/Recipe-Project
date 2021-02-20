package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by sousaJ on 02/01/2021
 * in package - com.sbtraining.recipe_project.services
 **/

public interface ImageService {
    void saveImageFile(RecipeCommand command, MultipartFile any);
    HttpServletResponse getRecipeImage(Long id, HttpServletResponse response);
}
