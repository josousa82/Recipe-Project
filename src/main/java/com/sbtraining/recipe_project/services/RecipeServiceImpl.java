package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.converters.RecipeCommandToRecipe;
import com.sbtraining.recipe_project.converters.RecipeToRecipeCommand;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by sousaJ on 01/10/2020
 * in package - com.sbtraining.recipe_project.services
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;



    public Set<Recipe> getAllRecipes() {

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe getRecipeById(Long id)  {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isEmpty()) {
            throw new RecipeNotFoundException("Recipe not found. For ID  with value: " + id.toString());
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand, BindingResult bindingResult) {
        return getConvertedRecipeCommand(recipeCommand);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        return getConvertedRecipeCommand(recipeCommand);
    }

    @Transactional
    protected RecipeCommand getConvertedRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        if (detachedRecipe == null) throw new RecipeNotFoundException("");

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Recipe save with id {}", savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public Recipe saveRecipe(Recipe recipe) {
           return recipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id)  {
        return recipeToRecipeCommand.convert(getRecipeById(id));
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }


}
