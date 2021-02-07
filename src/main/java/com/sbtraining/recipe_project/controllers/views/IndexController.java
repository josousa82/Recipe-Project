package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.services.CategoryService;
import com.sbtraining.recipe_project.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sousaJ on 17/09/2020
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes" ,recipeService.getAllRecipes());
        model.addAttribute("categories", categoryService.getAllDistinctCategoriesDescription());
        return "index2";
    }
}
