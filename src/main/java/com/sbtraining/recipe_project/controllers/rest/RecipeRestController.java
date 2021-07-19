package com.sbtraining.recipe_project.controllers.rest;

import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by sousaJ on 08/09/2020
 * in package - com.sbtraining.recipe_project.controllers
 **/
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeRestController {

    private final RecipeService recipeService;

    @GetMapping("/allRecipes")
    public Set<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }
}
