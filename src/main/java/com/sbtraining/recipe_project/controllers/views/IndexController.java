package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.repositories.CategoryRepository;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import com.sbtraining.recipe_project.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by sousaJ on 17/09/2020
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes" ,recipeService.getRecipes());
        return "index";
    }
}
