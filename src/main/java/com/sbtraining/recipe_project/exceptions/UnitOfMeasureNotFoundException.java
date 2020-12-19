package com.sbtraining.recipe_project.exceptions;

/**
 * Created by sousaJ on 09/12/2020
 * in package - com.sbtraining.recipe_project.exceptions
 **/
public class UnitOfMeasureNotFoundException extends Exception {

    public String message;

    public UnitOfMeasureNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
