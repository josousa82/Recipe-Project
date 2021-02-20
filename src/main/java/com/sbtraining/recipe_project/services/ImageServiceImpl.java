package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.converters.utilsConverters.BytesToFileConverter;
import com.sbtraining.recipe_project.converters.utilsConverters.MultipartFileToBytesConverter;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.model.Recipe;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by sousaJ on 02/01/2021
 * in package - com.sbtraining.recipe_project.services
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageServiceImpl implements ImageService {

    private final RecipeService recipeService;
    private final MultipartFileToBytesConverter imageToBytesConverter;
    private final BytesToFileConverter byteToImage;


    @Override
    @Transactional
    public void saveImageFile(RecipeCommand command, MultipartFile multipartFile) {
        try {
            Recipe recipe = recipeService.getRecipeById(command.getId());
            recipe.setImage(imageToBytesConverter.convert(multipartFile));
            recipeService.saveRecipe(recipe);
        } catch (RecipeNotFoundException | NotFoundException e) {
            log.error("Recipe not found with id {}", command.getId());
            e.printStackTrace();
        }
    }

    public HttpServletResponse getRecipeImage(Long id, HttpServletResponse response) {
        try {
            var recipeCommand = recipeService.getRecipeById(id);
            if (Objects.isNull(recipeCommand.getImage())) {
                recipeCommand.setImage(new byte[0]);
            } else {
                IOUtils.copy(Objects.requireNonNull(byteToImage.convert(recipeCommand.getImage())), response.getOutputStream());
            }

        } catch (NotFoundException | IOException | RecipeNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

}
