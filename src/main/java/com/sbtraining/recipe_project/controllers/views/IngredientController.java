package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.services.RecipeService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by sousaJ on 21/11/2020
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;


    public IngredientController(RecipeService recipeService1) {
        this.recipeService = recipeService1;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model){
        log.debug("Getting the ingredient list for recipe id : ${}", id);
        try {
            model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        } catch (NotFoundException e) {
            log.error("Ingredients for requested recipe not found {} ", e.getMessage());
            e.printStackTrace();
        }
        return "recipe/ingredients/list";
    }
}
