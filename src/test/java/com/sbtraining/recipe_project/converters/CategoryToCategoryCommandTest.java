package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void testConvert() {
        var category = Category.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .build();

        var categoryCommandConverted = converter.convert(category);

        assertNotNull(categoryCommandConverted);
        assertEquals(ID_VALUE, categoryCommandConverted.getId());
        assertEquals(DESCRIPTION, categoryCommandConverted.getDescription());
    }
}