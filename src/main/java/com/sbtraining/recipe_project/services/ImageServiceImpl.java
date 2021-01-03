package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by sousaJ on 02/01/2021
 * in package - com.sbtraining.recipe_project.services
 **/
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try {
            Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> {
                log.error("Recipe not found with id {}", recipeId);
               return new RecipeNotFoundException("Recipe wit id " + recipeId + " not found.");
            });

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for (byte b: file.getBytes()){
                byteObjects[i++] = b;
            }
            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);
        }catch (RecipeNotFoundException | IOException e){
            log.error("Recipe not found with id {}", recipeId);
            e.printStackTrace();
        }
    }

}
