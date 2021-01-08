package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import com.sbtraining.recipe_project.services.helper.ImageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    ImageUtils imageUtils;

    ImageService imageService;

    Recipe recipe;
    MultipartFile multipartFile;

    private final Long RECIPE_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository, imageUtils);

        recipe = Recipe.builder()
                .id(RECIPE_ID)
                .build();

        multipartFile = new MockMultipartFile("imageFile",
                                      "testing.txt", "text/plain", "Test file".getBytes());
    }

    @Test
    void saveImageFile() throws IOException {
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
//        when(imageUtils.compressBytes(multipartFile.getBytes())).then(invocation -> {
//        });

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(RECIPE_ID, multipartFile);

        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe =argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().getImageBytes().length);
    }


}