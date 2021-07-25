package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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


    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try {
            var command = recipeService.findCommandById(recipeId);
            command.setImage(convertMultipartFileToBytes(file));
            recipeService.saveRecipeCommand(command);
        }catch (RecipeNotFoundException | IOException e){
            log.error("Recipe not found with id {}", recipeId);
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getImageByRecipeId(Long id)  {
        var recipeCommand = recipeService.findCommandById(id);
        if(Objects.isNull(recipeCommand.getImage())){
            recipeCommand.setImage(new Byte[0]);
            //TODO java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0] with root cause
            return new byte[]{recipeCommand.getImage()[0]};
        }else {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;

            for (Byte b : recipeCommand.getImage()) {
                byteArray[i++] = b;
            }
            return byteArray;
        }
    }


    private Byte[] convertMultipartFileToBytes(MultipartFile file) throws IOException {
        Byte[] byteObjects = new Byte[file.getBytes().length];
        int i = 0;

        for (byte b: file.getBytes()){
            byteObjects[i++] = b;
        }
        return byteObjects;
    }


}
