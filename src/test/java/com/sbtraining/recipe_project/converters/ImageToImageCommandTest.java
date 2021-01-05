package com.sbtraining.recipe_project.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class ImageToImageCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";

    Byte[] imageBytes;

    MultipartFile multipartFile;

    private ImageToImageCommand converter;

    @BeforeEach
    void setUp() {
        converter = new ImageToImageCommand();
        multipartFile = new MockMultipartFile("imageFile",
                                              "testing.txt", "text/plain", "Test file".getBytes());
    }

    @Test
    void convert() {
    }
}