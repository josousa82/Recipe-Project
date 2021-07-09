package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.services.RecipeService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.function.Consumer;

import static com.sbtraining.recipe_project.controllers.views.ViewsLinksEnum.*;

/**
 * Created by sousaJ on 02/11/2020
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeViewsController {

    private final RecipeService recipeService;
    private String recipe = "recipe";

     private Consumer<ObjectError> bindingResultConsumer = objectError -> log.error(objectError.toString());


    @GetMapping("/recipe/{id}/show/")
    public String getRecipe(@PathVariable String id, Model model) throws NotFoundException {
        model.addAttribute(recipe, recipeService.getRecipeById(Long.valueOf(id)));
        return RECIPE_SHOW.getLink();
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute(recipe, new RecipeCommand());
        return RECIPE_FORM.getLink();
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) throws NotFoundException {
        model.addAttribute(recipe, recipeService.findCommandById(Long.valueOf(id)));
        return RECIPE_FORM.getLink();
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult)  {

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(bindingResultConsumer);
            return RECIPE_FORM.getLink();
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return (RECIPE_REDIRECT.getLink() + savedCommand.getId() + "/show/") ;
    }

    @GetMapping("recipe/{id}/delete")
    public String  deleteRecipeById(@PathVariable String id) {
        recipeService.deleteRecipeById(Long.valueOf(id));
        return RECIPE_REDIRECT_ALL.getLink();
    }

}
