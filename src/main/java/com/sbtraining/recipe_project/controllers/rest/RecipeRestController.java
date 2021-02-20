package com.sbtraining.recipe_project.controllers.rest;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.services.RecipeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sousaJ on 08/09/2020
 * in package - com.sbtraining.recipe_project.controllers
 **/
@RestController
@RequestMapping("/rest")
public class RecipeRestController {

    private final RecipeService recipeService;

    public RecipeRestController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest(Model model) {
        model.addAttribute("test", "Teste");
        return Objects.requireNonNull(model.getAttribute("test")).toString();
    }

    @GetMapping("/allRecipes")
    public Set<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipeService.getAllRecipes().iterator().forEachRemaining(recipes::add);
        return recipeService.getAllRecipes();
    }


}
