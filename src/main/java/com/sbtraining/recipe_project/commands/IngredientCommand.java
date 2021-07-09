package com.sbtraining.recipe_project.commands;

import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by sousaJ on 08/11/2020
 * in package - com.sbtraining.recipe_project.commands
 **/

@Setter
@Getter
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
}
