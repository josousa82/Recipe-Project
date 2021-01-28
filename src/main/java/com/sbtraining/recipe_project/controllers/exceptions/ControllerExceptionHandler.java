package com.sbtraining.recipe_project.controllers.exceptions;

import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sousaJ on 28/01/2021
 * in package - com.sbtraining.recipe_project.controllers.exceptions
 **/
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView handleRecipeNotFound(Exception ex){
        log.error("Recipe not found.");
        log.error(ex.getMessage());
        var mv  = new ModelAndView();
        mv.addObject("exception", ex);
        mv.setViewName("errors/404Error");
        return mv;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception ex){
        log.error("Request number format not valid. Expected a number : {}", ex.getMessage());
        log.error(ex.getMessage());
        var mv  = new ModelAndView();
        mv.addObject("exception", ex);
        mv.setViewName("errors/400Error");
        return mv;
    }
}
