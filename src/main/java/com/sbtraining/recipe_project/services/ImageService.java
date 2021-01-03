package com.sbtraining.recipe_project.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sousaJ on 02/01/2021
 * in package - com.sbtraining.recipe_project.services
 **/

public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile any);
}
