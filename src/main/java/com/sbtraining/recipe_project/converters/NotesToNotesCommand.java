package com.sbtraining.recipe_project.converters;

import com.sbtraining.recipe_project.commands.NotesCommand;
import com.sbtraining.recipe_project.model.Notes;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 12/11/2020
 * in package - com.sbtraining.recipe_project.converters
 **/
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {

        if(notes == null) return null;

        return NotesCommand.builder()
                .id(notes.getId())
                .recipeNotes(notes.getRecipeNotes())
                .build();
    }

}
