package com.sbtraining.recipe_project.converters;


import com.sbtraining.recipe_project.model.Notes;
import com.sbtraining.recipe_project.utils.TestNotesUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String NOTES = "notes 1";

    Notes notesToConvert;


    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        notesToConvert = TestNotesUtils.notes_1;

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