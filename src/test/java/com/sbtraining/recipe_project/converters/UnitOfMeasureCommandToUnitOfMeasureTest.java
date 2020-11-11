package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final Long UOM_ID = 1L;
    public static final String DESCRIPTION = "tablespoon";


    UnitOfMeasureCommandToUnitOfMeasure converter;
    UnitOfMeasureCommand uomToConvert;


    @BeforeEach
    void setUp() {

        uomToConvert = UnitOfMeasureCommand.builder()
                .id(UOM_ID)
                .description(DESCRIPTION)
                .build();

        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testForNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void testConvertUom() {

        var uom = converter.convert(uomToConvert);

        assertNotNull(uom);
        assertEquals(UOM_ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }
}