package com.sbtraining.recipe_project.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by sousaJ on 04/11/2020
 * in package - com.sbtraining.recipe_project.commands
 **/

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;

    @Builder
    public CategoryCommand(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
