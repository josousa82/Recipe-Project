package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.CategoryCommand;
import com.sbtraining.recipe_project.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() throws Exception {

        CategoryCommand categoryCommand = CategoryCommand.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .build();

        Category categoryConverted =  converter.convert(categoryCommand);

        assert categoryConverted != null;
        assertEquals(ID_VALUE, categoryConverted.getId());
        assertEquals(DESCRIPTION, categoryConverted.getDescription());
    }
}