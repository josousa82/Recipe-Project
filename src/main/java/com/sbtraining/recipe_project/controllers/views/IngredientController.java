package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.services.IngredientService;
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
    private final IngredientService ingredientService;


    public IngredientController(RecipeService recipeService1, IngredientService ingredientService) {
        this.recipeService = recipeService1;
        this.ingredientService = ingredientService;
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

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String ingredientId,
                                       Model model){
        try {
            model.addAttribute("ingredient", ingredientService
                    .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        } catch (NotFoundException | RecipeNotFoundException  e) {
            log.error("Ingredient with id {} for recipe with id {} not found {} ", recipeId, ingredientId, e.getMessage());
            e.printStackTrace();
        }
        return "recipe/ingredients/show";
    }

}
