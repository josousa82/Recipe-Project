package com.sbtraining.recipe_project.commands;


import com.sbtraining.recipe_project.model.enums.Difficulty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sousaJ on 08/11/2020
 * in package - com.sbtraining.recipe_project.commands
 **/

@Builder
@Getter
@Setter
@Slf4j
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

    @Builder.Default
    private Set<IngredientCommand> ingredients = new HashSet<>();


    private Byte[] image;
    private Difficulty difficulty;
    private NotesCommand notes;

    @Builder.Default
    private Set<CategoryCommand> categories = new HashSet<>();

    public RecipeCommand addIngredientCommand(IngredientCommand ingredientCommand){
        if(Objects.isNull(ingredientCommand)) {
            log.error("IngredientCommand cannot be null");
            throw new IllegalArgumentException("IngredientCommand cannot be null");
        } else {
            ingredientCommand.setRecipeId(this.id);
            this.ingredients.add(ingredientCommand);
            return this;
        }
    }

    public RecipeCommand addCategoryCommand(CategoryCommand categoryCommand) {
        if(Objects.isNull(categoryCommand)) {
            log.error("CategoryCommand cannot be null");
            throw new IllegalArgumentException("CategoryCommand cannot be null");
        } else {
            this.categories.add(categoryCommand);
            return this;
        }
    }

}
