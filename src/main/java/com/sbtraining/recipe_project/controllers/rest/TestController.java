package com.sbtraining.recipe_project.controllers.rest;

import com.sbtraining.recipe_project.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sousaJ on 08/09/2020
 * in package - com.sbtraining.recipe_project.controllers
 **/
@Controller
@RequestMapping("/rest")
public class TestController {

    private final RecipeService recipeService;

    public TestController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String getTest(Model model){
//        model.addAttribute("test", "Teste");
//        return Objects.requireNonNull(model.getAttribute("test")).toString();
//    }

//    @RequestMapping(value = "/allRecipes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Set<Recipe> getAllRecipes(){
//        Set<Recipe> recipes = new HashSet<>();
//        recipeService.getAllRecipes().iterator().forEachRemaining(recipes::add);
//        return recipes;
//    }


}
