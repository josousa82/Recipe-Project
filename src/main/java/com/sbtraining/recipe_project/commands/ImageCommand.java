package com.sbtraining.recipe_project.commands;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by sousaJ on 03/01/2021
 * in package - com.sbtraining.recipe_project.commands
 **/
@Builder
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class ImageCommand {
    private Long id;
    private Long recipeId;
    private String name;
    private String type;
    private Byte[] imageBytes;
}
