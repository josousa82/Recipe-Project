package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sousaJ on 02/11/2020
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Controller
public class RecipeViewsController {

    private final RecipeService recipeService;

    public RecipeViewsController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String getRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }
}
