package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.services.RecipeService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sousaJ on 02/11/2020
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Slf4j
@Controller
public class RecipeViewsController {

    private final RecipeService recipeService;
    private String recipe = "recipe";

    public RecipeViewsController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show/")
    public String getRecipe(@PathVariable String id, Model model) throws NotFoundException {
        model.addAttribute(recipe, recipeService.getRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute(recipe, new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) throws NotFoundException {
        model.addAttribute(recipe, recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command)  {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return ("redirect:/recipe/" + savedCommand.getId() + "/show/") ;
    }

    @GetMapping("recipe/{id}/delete")
    public String  deleteRecipeById(@PathVariable String id) {
        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView handleRecipeNotFound(Exception ex){
        log.error("Recipe not found.");
        log.error(ex.getMessage());
        var mv  = new ModelAndView();
        mv.addObject("exception", ex);
        mv.setViewName("404Error");
        return mv;
    }
}
