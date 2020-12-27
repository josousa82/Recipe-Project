package com.sbtraining.recipe_project.services.ITServiceTests;

import com.sbtraining.recipe_project.converters.IngredientCommandToIngredient;
import com.sbtraining.recipe_project.converters.IngredientToIngredientCommand;
import com.sbtraining.recipe_project.model.Ingredient;
import com.sbtraining.recipe_project.repositories.IngredientRepository;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import com.sbtraining.recipe_project.services.IngredientService;
import com.sbtraining.recipe_project.services.IngredientServiceImpl;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by sousaJ on 26/12/2020
 * in package - com.sbtraining.recipe_project.services.ITServiceTests
 **/
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IngredientServiceIT {


    @Autowired
    private  IngredientRepository ingredientRepository;

    @Autowired
    private  IngredientCommandToIngredient ingredientCommandToIngredient;

    @Autowired
    private  IngredientToIngredientCommand ingredientToIngredientCommand;

    @Autowired
    private  RecipeRepository recipeRepository;

    @Autowired
    private  UnitOfMeasureRepository uomRepository;

    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        ingredientService = new IngredientServiceImpl(ingredientRepository, ingredientCommandToIngredient,
                                                      ingredientToIngredientCommand, recipeRepository,
                                                      uomRepository);
    }

    @Test
    void getAllIngredients() {
    }

    @Test
    void findAllRecipeIngredientsByRecipeId() {
    }

    @Test
    void getIngredientById() {

    }

    @Test
    @Transactional
    void getIngredientByIdThrowsNotfoundException() {
        Exception thrown = assertThrows(NotFoundException.class,
                                                () -> ingredientService.getIngredientById(30L));
        assertEquals("Ingredient not found", thrown.getMessage());
    }

    @Test
    @Transactional
    void getIngredientByIdDoesNotThrowNullPointerException() {
        Long ingredientToSearch = (ingredientRepository.findAll(Sort.by("id")).size() + 1L);
        Exception thrown = assertThrows(NotFoundException.class,
                                        () -> {
                                            log.info("Test NotFoundException for ingredient with id {}", ingredientToSearch);
                                            ingredientService.getIngredientById(ingredientToSearch);
                                        });

        assertEquals("Ingredient not found", thrown.getMessage());
    }


    @Test
    @Transactional
    void deleteIngredientById()  {
        List<Ingredient> ingredients = ingredientRepository.findAll(Sort.by("id"));
        Ingredient ingredientToDelete = ingredients.iterator().next();
        Integer initialListSize = ingredients.size();

        assert  ingredientToDelete != null;
        ingredientService.deleteIngredientById(ingredientToDelete.getId());
        List<Ingredient> ingredientsResult = ingredientRepository.findAll(Sort.by("id"));
        Integer resultListSize = ingredientsResult.size();

        assertEquals(initialListSize - 1, resultListSize);

    }

    @Test
    @Transactional
    void deleteByRecipeIdAndId()  {
        List<Ingredient> ingredients = ingredientRepository.findAll(Sort.by("id"));
        Ingredient ingredientToDelete = ingredients.iterator().next();
        Integer initialListSize = ingredients.size();

        assert  ingredientToDelete != null;
        ingredientService.deleteByRecipeIdAndId(ingredientToDelete.getRecipe().getId(), ingredientToDelete.getId());
        List<Ingredient> ingredientsResult = ingredientRepository.findAll(Sort.by("id"));
        Integer resultListSize = ingredientsResult.size();

        assertEquals(initialListSize - 1, resultListSize);

    }




    @Test
    void findAllByRecipeId() {
    }

    @Test
    void saveIngredientCommand() {
    }

    @Test
    void findIngredientCommandById() {
    }

    @Test
    void findByRecipeIdAndIngredientId() {
    }
}
