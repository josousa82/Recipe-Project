package com.sbtraining.recipe_project.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

/**
 * Created by sousaJ on 09/12/2020
 * in package - com.sbtraining.recipe_project.exceptions
 **/

@Getter
@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException {

    private String message = "Recipe not found." ;


    public RecipeNotFoundException(String message) {
        super(message);
        if(Objects.nonNull(message))
            this.message = message;
    }

    public RecipeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
