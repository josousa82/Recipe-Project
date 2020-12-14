package com.sbtraining.recipe_project.exceptions;

import javassist.NotFoundException;

/**
 * Created by sousaJ on 13/12/2020
 * in package - com.sbtraining.recipe_project.exceptions
 **/
public class IngredientNotFoundException extends NotFoundException {

    public IngredientNotFoundException(String msg) {
        super(msg);
    }

    public IngredientNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
