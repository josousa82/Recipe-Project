package com.sbtraining.recipe_project.commands;

import com.sbtraining.recipe_project.model.enums.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sousaJ on 08/11/2020
 * in package - com.sbtraining.recipe_project.commands
 **/

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredientsC = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NotesCommand notesC;
    private Set<CategoryCommand> categoriesC = new HashSet<>();

    public RecipeCommand addIngredientCommand(IngredientCommand ingredientCommand){
        this.ingredientsC.add(ingredientCommand);
        return this;
    }
}
