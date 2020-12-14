package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.converters.IngredientCommandToIngredient;
import com.sbtraining.recipe_project.converters.IngredientToIngredientCommand;
import com.sbtraining.recipe_project.exceptions.IngredientNotFoundException;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.model.Ingredient;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.repositories.IngredientRepository;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by sousaJ on 21/11/2020
 * in package - com.sbtraining.recipe_project.services
 **/
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientCommandToIngredient ingredientCommandToIngredient, IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        var ingredientsList = new ArrayList<Ingredient>();
        ingredientRepository.findAll().iterator().forEachRemaining(ingredientsList::add);
        return ingredientsList;
    }

    @Override
    public Ingredient getIngredientById(Long id) throws NotFoundException {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) throw new NotFoundException("Ingredient not found");
        return ingredient.get();
    }

    @Override
    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Ingredient detachedIngredient = ingredientCommandToIngredient.convert(command);
        Ingredient savedIngredient = ingredientRepository.save(Objects.requireNonNull(detachedIngredient));
        return ingredientToIngredientCommand.convert(savedIngredient);
    }

    @Override
    @Transactional
    public IngredientCommand findIngredientCommandById(Long id) throws NotFoundException {
        return ingredientToIngredientCommand.convert(getIngredientById(id));
    }

    @Override
    @Transactional
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) throws RecipeNotFoundException, NotFoundException {

       Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
               () -> new RecipeNotFoundException("Recipe not found, null ingredients for null recipe."));

         Optional<IngredientCommand>  ingredientCommandOptional = recipe.getIngredients().stream()
                  .filter(ingredient -> ingredient.getId().equals(ingredientId))
                  .map(ingredientToIngredientCommand::convert)
                  .findFirst(); // unstable use of findFirst() in stream, refer to known issues and correct

            return ingredientCommandOptional.orElseThrow(()-> new IngredientNotFoundException("Ingredient not found with recipe id: " + ingredientId));
    }
}
