package com.sbtraining.recipe_project.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by sousaJ on 08/11/2020
 * in package - com.sbtraining.recipe_project.commands
 **/

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uomc;


}
