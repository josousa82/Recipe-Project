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
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {

        if (notesCommand == null) return null;

        return Notes.builder()
                .id(notesCommand.getId())
                .recipeNotes(notesCommand.getRecipeNotes())
                .build();
    }
}
