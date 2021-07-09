package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.converters.IngredientCommandToIngredient;
import com.sbtraining.recipe_project.converters.IngredientToIngredientCommand;
import com.sbtraining.recipe_project.exceptions.IngredientNotFoundException;
import com.sbtraining.recipe_project.exceptions.RecipeNotFoundException;
import com.sbtraining.recipe_project.exceptions.UnitOfMeasureNotFoundException;
import com.sbtraining.recipe_project.model.Ingredient;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.repositories.IngredientRepository;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    private final UnitOfMeasureRepository uomRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository uomRepository) {

        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        var ingredientsList = new ArrayList<Ingredient>();
        ingredientRepository.findAll().iterator().forEachRemaining(ingredientsList::add);

        return ingredientsList;
    }

    @Override
    public List<IngredientCommand> findAllRecipeIngredientsByRecipeId(Long recipeId) {
        var ingredientsList = new ArrayList<IngredientCommand>();
        ingredientRepository.findAllByRecipeId(recipeId, Sort.by("id"))
                            .iterator()
                            .forEachRemaining(e -> ingredientsList.add(ingredientToIngredientCommand.convert(e)));
        return ingredientsList;
    }

    @Override
    public Ingredient getIngredientById(Long id) throws NotFoundException {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) throw new NotFoundException("Ingredient not found");
        return ingredient.get();
    }

    @Override
    @Transactional
    public void deleteIngredientById(Long id) {

        ingredientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByRecipeIdAndId(Long recipeId, Long id) throws RecipeNotFoundException, IngredientNotFoundException {
        if(Objects.nonNull(recipeId)) {
            if (Objects.nonNull(id)) {
                ingredientRepository.deleteByRecipeIdAndId(recipeId, id);
            }else {
                throw new IngredientNotFoundException("Ingredient with id " + id +" not found");
            }
        }else {
            throw new RecipeNotFoundException("Recipe with id " + recipeId +" not found");
        }
    }

    @Override
    public List<Ingredient> findAllByRecipeId(Long recipeId) {
        return findAllByRecipeId(recipeId);
    }


    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) throws UnitOfMeasureNotFoundException, IngredientNotFoundException {

        Optional<Recipe> recipeOptional =  recipeRepository.findById(command.getRecipeId());

        if(recipeOptional.isEmpty()){
            //TODO error if not found
            log.error("Recipe not found for id: {}", command.getRecipeId());
            return new IngredientCommand();
        }else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription())
                        .setUom(uomRepository.findById(command.getUom().getId())
                                .orElseThrow(() -> {
                                    log.error("Unit of measure not found to ingredient with id {}", ingredientFound.getId());
                                    //TODO deal with error
                                    return new UnitOfMeasureNotFoundException("Unit Of Measure not found, when saving ingredient.");
                                }))
                        .setAmount(command.getAmount());

            }else {
                // TODO Refactor method, extract save to one method and update to another
                // add new ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);

                Assert.notNull(ingredient, "Ingredient cannot be null.");
//                assert ingredient != null;
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredient -> {
                        if(Objects.nonNull(recipeIngredient.getId()) && Objects.nonNull(command.getId())){
                            return recipeIngredient.getId().equals(command.getId());
                        }else {
                            return false;
                        }
                    })
                    .findFirst();

            // TODO Refactor method, extract piece (checking if ingredient was saved on the recipe code smell, don't like it)
            if(savedIngredientOptional.isEmpty()){
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getDescription().equalsIgnoreCase(command.getDescription()))
                        .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            return ingredientToIngredientCommand.convert(savedIngredientOptional.orElseThrow(() -> {
                                    //TODO deal with error
                log.error("Ingredient with id {} not found in recipe with id {}.", command.getId(), recipe.getId());
                return new IngredientNotFoundException("Ingredient not found. Ingredient saveOrUpdate method.");
            }));
        }
    }

    @Override
    @Transactional
    public IngredientCommand findIngredientCommandById(Long id) throws NotFoundException {
        return ingredientToIngredientCommand.convert(getIngredientById(id));
    }

    @Override
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
