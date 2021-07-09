package com.sbtraining.recipe_project.converters;


import com.sbtraining.recipe_project.model.Ingredient;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "flower";
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Long UOM_ID = 1L;
    public static final Recipe RECIPE = Recipe.builder().id(1L).build();

    IngredientToIngredientCommand converter;
    UnitOfMeasure uom;
    Ingredient ingredientToConvert;

    @BeforeEach
    void setUp() {

        uom = UnitOfMeasure.builder()
                .id(UOM_ID)
                .build();

        ingredientToConvert = Ingredient.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .amount(AMOUNT)
                .uom(uom)
                .recipe(RECIPE)
                .build();

        converter = IngredientToIngredientCommand.builder()
                .uomConverter(new UnitOfMeasureToUnitOfMeasureCommand())
                .build();

    }

    @Test
    void testForNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void testConvertNullUom() {

        ingredientToConvert.setUom(null);

        // when
        var ingredientCommand = converter.convert(ingredientToConvert);

        // then
        assert ingredientCommand != null;
        assertNull(ingredientCommand.getUom());
        assertNotNull(ingredientCommand);
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }

    @Test
    void testConvertWithUom() {
        // when
        var ingredientCommandConverted = converter.convert(ingredientToConvert);

        // then
        assertNotNull(ingredientCommandConverted);
        assertEquals(ID_VALUE, ingredientCommandConverted.getId());
        assertEquals(AMOUNT, ingredientCommandConverted.getAmount());
        assertEquals(DESCRIPTION, ingredientCommandConverted.getDescription());
        assertEquals(UOM_ID, ingredientCommandConverted.getUom().getId());
    }
}