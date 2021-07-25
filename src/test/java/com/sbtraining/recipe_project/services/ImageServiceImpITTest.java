package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.converters.CategoryToCategoryCommand;
import com.sbtraining.recipe_project.converters.IngredientToIngredientCommand;
import com.sbtraining.recipe_project.converters.NotesToNotesCommand;
import com.sbtraining.recipe_project.converters.RecipeToRecipeCommand;
import com.sbtraining.recipe_project.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImpITTest {

    @Mock
    RecipeService recipeService;

    ImageService imageService;

    RecipeCommand recipeCommand;

    @Mock
    CategoryToCategoryCommand categoryToCategoryCommand;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    NotesToNotesCommand notesToNotesCommand;

    RecipeToRecipeCommand recipeToCommand;

    Recipe recipe;
    MultipartFile multipartFile;

    private final Long RECIPE_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        imageService = new ImageServiceImpl(recipeService);

        recipe = Recipe.builder()
                .id(RECIPE_ID)
                .build();
        recipeToCommand = new RecipeToRecipeCommand(categoryToCategoryCommand, ingredientToIngredientCommand,notesToNotesCommand );
        multipartFile = new MockMultipartFile("imageFile",
                                      "testing.txt", "text/plain", "Test file".getBytes());
    }

    @Test
    void saveImageFile() throws IOException {
        RecipeCommand command = recipeToCommand.convert(recipe);
        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        ArgumentCaptor<RecipeCommand> argumentCaptor = ArgumentCaptor.forClass(RecipeCommand.class);

        imageService.saveImageFile(RECIPE_ID, multipartFile);

        verify(recipeService, times(1)).saveRecipeCommand(argumentCaptor.capture());
        RecipeCommand savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }


}