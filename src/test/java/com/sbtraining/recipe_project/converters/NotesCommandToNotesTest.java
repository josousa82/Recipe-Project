package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.NotesCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static final Long ID_VALUE = 1L;
    public static final String NOTES = "flower";

    NotesCommand notesToConvert;


    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        notesToConvert = NotesCommand.builder()
                .id(ID_VALUE)
                .recipesNotes(NOTES)
                .build();

        converter = new NotesCommandToNotes();

    }

    @Test
    void testForNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void testConvertNotesToCommandNotes() {
        // when
        var notesCommandConverted = converter.convert(notesToConvert);

        // then
        assertNotNull(notesCommandConverted);
        assertEquals(ID_VALUE, notesCommandConverted.getId());
        assertEquals(NOTES, notesCommandConverted.getRecipeNotes());
    }

}