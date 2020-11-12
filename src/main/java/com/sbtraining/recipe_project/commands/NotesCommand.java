package com.sbtraining.recipe_project.commands;

import lombok.*;

/**
 * Created by sousaJ on 08/11/2020
 * in package - com.sbtraining.recipe_project.commands
 **/

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotesCommand {
    private Long id;
    private String recipesNotes;
}
