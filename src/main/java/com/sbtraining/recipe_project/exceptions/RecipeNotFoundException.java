package com.sbtraining.recipe_project.exceptions;

/**
 * Created by sousaJ on 09/12/2020
 * in package - com.sbtraining.recipe_project.exceptions
 **/
public class RecipeNotFoundException extends Exception {

    public String message;

    public RecipeNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
