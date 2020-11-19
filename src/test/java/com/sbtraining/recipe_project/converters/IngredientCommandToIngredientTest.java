package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import com.sbtraining.recipe_project.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "flower";
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Long UOM_ID = 1L;
    public static final Recipe RECIPE = Recipe.builder().id(1L).build();

    IngredientCommandToIngredient converter;
    UnitOfMeasureCommand uomCommand;
    IngredientCommand ingredientCommandToConvert;

    @BeforeEach
    void setUp() {
        uomCommand = UnitOfMeasureCommand.builder()
                .id(UOM_ID)
                .build();

        ingredientCommandToConvert = IngredientCommand.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .amount(AMOUNT)
                .uom(uomCommand)
                .recipeId(RECIPE.getId())
                .build();

       converter = IngredientCommandToIngredient.builder()
               .uomConverter(new UnitOfMeasureCommandToUnitOfMeasure())
               .build();

    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testConvertNullUomc() {
        ingredientCommandToConvert.setUom(null);

        var ingredientConverted = converter.convert(ingredientCommandToConvert);

        assert ingredientConverted != null;
        assertNull(ingredientConverted.getUom());
        assertNotNull(ingredientConverted);
        assertEquals(ID_VALUE, ingredientConverted.getId());
        assertEquals(AMOUNT, ingredientConverted.getAmount());
        assertEquals(DESCRIPTION, ingredientConverted.getDescription());
    }

    @Test
    void testConvertWithUomc() {

        var ingredientConverted = converter.convert(ingredientCommandToConvert);

        assertNotNull(ingredientConverted);
        assertEquals(ID_VALUE, ingredientConverted.getId());
        assertEquals(AMOUNT, ingredientConverted.getAmount());
        assertEquals(DESCRIPTION, ingredientConverted.getDescription());
        assertEquals(UOM_ID, ingredientConverted.getUom().getId());
    }
}