package com.sbtraining.recipe_project.exceptions;

/**
 * Created by sousaJ on 26/01/2021
 * in package - com.sbtraining.recipe_project.exceptions
 **/
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
