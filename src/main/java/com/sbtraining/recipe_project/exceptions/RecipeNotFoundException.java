package com.sbtraining.recipe_project.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Created by sousaJ on 09/12/2020
 * in package - com.sbtraining.recipe_project.exceptions
 **/
@Getter
@Setter
public class RecipeNotFoundException extends Exception {

    private String message = "Recipe not found." ;

    public RecipeNotFoundException(String message) {
        super(message);
        if(Objects.nonNull(message))
            this.message = message;
    }
}
