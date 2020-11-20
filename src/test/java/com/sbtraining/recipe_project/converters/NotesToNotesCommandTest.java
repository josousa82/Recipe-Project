package com.sbtraining.recipe_project.converters;


import com.sbtraining.recipe_project.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String NOTES = "flower";

    Notes notesToConvert;


    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        notesToConvert = Notes.builder()
                .id(ID_VALUE)
                .recipeNotes(NOTES)
                .build();

        converter = new NotesToNotesCommand();

    }

    @Test
    void testForNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void testConvertNotesToCommandNotes() {

        // given


        // when
        var notesCommandConverted = converter.convert(notesToConvert);


        // then
        assertNotNull(notesCommandConverted);
        assertEquals(ID_VALUE, notesCommandConverted.getId());
        assertEquals(NOTES, notesCommandConverted.getRecipeNotes());
    }
}