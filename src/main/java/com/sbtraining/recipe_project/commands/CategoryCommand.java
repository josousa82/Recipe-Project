package com.sbtraining.recipe_project.commands;

import lombok.*;

/**
 * Created by sousaJ on 04/11/2020
 * in package - com.sbtraining.recipe_project.commands
 **/
@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
}
