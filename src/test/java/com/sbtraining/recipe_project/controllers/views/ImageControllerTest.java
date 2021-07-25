package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.services.ImageService;
import com.sbtraining.recipe_project.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController controller;

    MockMvc mockMvc;
    RecipeCommand command;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new ImageController(recipeService, imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        command = new RecipeCommand();
        command.setId(1L);
    }

    @Test
    void getUploadForm() throws Exception {



        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image/uploadImage"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void handleImagePost() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image",
                                                            "testing.txt",
                                                            "text/plain",
                                                                "test file".getBytes());
        mockMvc.perform(multipart("/recipe/1/image/uploadImage/").file(mockMultipartFile))
               .andExpect(status().is3xxRedirection())
               .andExpect(header().string("Location", "/recipe/1/show/"));
        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    void renderImageFromDB() throws Exception {
        String fakeImage = "fake image";
        byte[] bytesFromDB = new byte[fakeImage.getBytes().length];
        int i = 0;
        for (byte prymByte : fakeImage.getBytes()){
            bytesFromDB[i++] = prymByte;
        }

        when(imageService.getImageByRecipeId(anyLong())).thenReturn(bytesFromDB);

        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/get/image/"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();
        assertEquals(fakeImage.getBytes().length, responseBytes.length );
    }

    // TODO Refactor image service, controller advice exceptions and tests
    @Disabled
    @Test
    void testGetImageNumberFormatException() throws Exception {
        when(imageService.getImageByRecipeId(anyLong())).thenThrow(NumberFormatException.class);
        mockMvc.perform(get("/recipe/asdf/get/image"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

}