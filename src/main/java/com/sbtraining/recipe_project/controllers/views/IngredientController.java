package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import com.sbtraining.recipe_project.exceptions.IngredientNotFoundException;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.exceptions.UnitOfMeasureNotFoundException;
import com.sbtraining.recipe_project.services.IngredientService;
import com.sbtraining.recipe_project.services.RecipeService;
import com.sbtraining.recipe_project.services.UnitOfMeasureService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sousaJ on 21/11/2020
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Slf4j
@Controller
public class IngredientController {

    private String INGREDIENT_MODEL = "ingredient";

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService1, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService1;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model){
        log.debug("Getting the ingredient list for recipe id : ${}", id);

        try {
            List<IngredientCommand> recipeIngredients = ingredientService.findAllRecipeIngredientsByRecipeId(Long.valueOf(id));
            model.addAttribute("recipeIngredients", recipeIngredients);
            model.addAttribute("recipeId", id);
        } catch (Exception e) {
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
            model.addAttribute(INGREDIENT_MODEL, ingredientService
                    .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        } catch (NotFoundException | RecipeNotFoundException  e) {
            log.error("Ingredient with id {} for recipe with id {} not found {} ", recipeId, ingredientId, e.getMessage());
            e.printStackTrace();
        }
        return "recipe/ingredients/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId,
                                         Model model) throws NotFoundException, RecipeNotFoundException {

        model.addAttribute(INGREDIENT_MODEL, ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());

        return "recipe/ingredients/ingredientForm";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) throws UnitOfMeasureNotFoundException, IngredientNotFoundException {

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.info("Saved recipe with id {}", savedCommand.getRecipeId());
        log.info("Saved ingredient with id {}", savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }


    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String createNewIngredient(@PathVariable String recipeId, Model model) throws NotFoundException {
            //TODO raise exception if null
//            RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

            IngredientCommand ingredientCommand = IngredientCommand.builder()
                    .recipeId(Long.valueOf(recipeId))
                    .uom(new UnitOfMeasureCommand())
                    .build();
            model.addAttribute(INGREDIENT_MODEL, ingredientCommand);
            model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredients/ingredientForm";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId) throws IngredientNotFoundException, RecipeNotFoundException {
        ingredientService.deleteByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        log.info("Deleted ingredient with id {} from recipe with id {}", ingredientId, recipeId);
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

}
