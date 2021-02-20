package com.sbtraining.recipe_project.commands;


import com.sbtraining.recipe_project.model.enums.Difficulty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 3,  max = 255)
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;
    private String source;

    @URL
    private String url;

    @NotBlank
    private String directions;

    @Builder.Default
    private Set<IngredientCommand> ingredients = new HashSet<>();



    private MultipartFile image;

    @Builder.Default
    private Difficulty difficulty = Difficulty.NOT_DEFINED;

    @Builder.Default
    private NotesCommand notes = NotesCommand.builder().build();

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
