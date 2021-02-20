package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.converters.utilsConverters.BytesToFileConverter;
import com.sbtraining.recipe_project.converters.utilsConverters.MultipartFileToBytesConverter;
import com.sbtraining.recipe_project.model.Recipe;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    RecipeService recipeService;

    HttpServletResponse servletResponse;




    MultipartFileToBytesConverter imageToBytesConverter;


    BytesToFileConverter byteToImage;

    ImageService imageService;

    Recipe recipe;
    RecipeCommand command;
    MultipartFile multipartFile;

    private final Long RECIPE_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

         imageToBytesConverter = new MultipartFileToBytesConverter();
         byteToImage = new BytesToFileConverter();
         imageService = new ImageServiceImpl(recipeService, imageToBytesConverter, byteToImage);

        recipe = Recipe.builder()
                .id(RECIPE_ID)
                .build();

        command = RecipeCommand.builder().id(RECIPE_ID).build();

        multipartFile = new MockMultipartFile("imageFile",
                                      "testing.txt", "text/plain", "Test file".getBytes());


    }

    @Test
    void saveImageFile() throws IOException, NotFoundException {
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(command, multipartFile);

        verify(recipeService, times(1)).saveRecipe(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();

        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);

    }

    @Test
    void getRecipeImageTest() throws NotFoundException {

        recipe.setImage(imageToBytesConverter.convert(multipartFile));

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        MockHttpServletResponse response = new MockHttpServletResponse();

        imageService.getRecipeImage(anyLong(),response);

        verify(recipeService, times(1)).findCommandById(anyLong());

    }


}