package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.converters.IngredientCommandToIngredient;
import com.sbtraining.recipe_project.converters.IngredientToIngredientCommand;
import com.sbtraining.recipe_project.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.sbtraining.recipe_project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.sbtraining.recipe_project.exceptions.IngredientNotFoundException;
import com.sbtraining.recipe_project.exceptions.UnitOfMeasureNotFoundException;
import com.sbtraining.recipe_project.model.Ingredient;
import com.sbtraining.recipe_project.model.Recipe;
import com.sbtraining.recipe_project.model.UnitOfMeasure;
import com.sbtraining.recipe_project.model.enums.Difficulty;
import com.sbtraining.recipe_project.repositories.IngredientRepository;
import com.sbtraining.recipe_project.repositories.RecipeRepository;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class IngredientServiceImpIT {

    public static final Long RECIPE_ID = 1L;
    public static final String RECIPE_DESCRIPTION = "flower";
    public static final Integer RECIPE_PREP_TIME = 30;
    public static final Integer RECIPE_COOK_TIME = 15;
    public static final Integer RECIPE_SERVINGS = 4;
    public static final String RECIPE_SOURCE = "flower";
    public static final String RECIPE_URL = "flower";
    public static final String RECIPE_DIRECTIONS = "flower";
    public static final Difficulty RECIPE_DIFFICULTY = Difficulty.EASY;
    private final Long INGREDIENT_ID = 1L;
    private final String INGREDIENT_DESCRIPTION = "Rice";
    private final String UOM_DESCRIPTION = "Teaspoon";
    private final BigDecimal INGREDIENT_AMOUNT = BigDecimal.valueOf(30L);
    private final Long UOM_ID = 1L;


    @Mock
    private IngredientRepository ingredientRepository;

    private IngredientCommandToIngredient ingredientCommandToIngredient;

    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UnitOfMeasureRepository uomRepository;


    private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private IngredientCommand command;
    private IngredientService ingredientService;
    private Ingredient ingredient1;
    private Set<Ingredient> ingredientSet;
    private Recipe recipe;
    private UnitOfMeasure uom;


    @BeforeEach
    void setUp() {

        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

        ingredientCommandToIngredient = new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
        ingredientToIngredientCommand = new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);

        MockitoAnnotations.initMocks(this);


        ingredientService = new IngredientServiceImpl(ingredientRepository, ingredientCommandToIngredient,
                                                      ingredientToIngredientCommand, recipeRepository, uomRepository);

        uom = UnitOfMeasure.builder()
                .id(UOM_ID)
                .description(UOM_DESCRIPTION)
                .build();

        ingredient1 = Ingredient.builder()
                .id(INGREDIENT_ID)
                .description(INGREDIENT_DESCRIPTION)
                .amount(INGREDIENT_AMOUNT)
                .uom(uom)
                .build();

        recipe = Recipe.builder()
                .id(RECIPE_ID)
                .description(RECIPE_DESCRIPTION)
                .prepTime(RECIPE_PREP_TIME)
                .cookTime(RECIPE_COOK_TIME)
                .servings(RECIPE_SERVINGS)
                .source(RECIPE_SOURCE)
                .url(RECIPE_URL)
                .directions(RECIPE_DIRECTIONS)
                .difficulty(RECIPE_DIFFICULTY)
                .build();

        recipe.addIngredient(ingredient1);

        command = IngredientCommand.builder()
                .id(INGREDIENT_ID)
                .description(INGREDIENT_DESCRIPTION)
                .amount(INGREDIENT_AMOUNT)
                .uom(unitOfMeasureToUnitOfMeasureCommand.convert(uom))
                .recipeId(recipe.getId())
                .build();

        ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient1);

    }

    @Test
    void testGetAllIngredients() {

        when(ingredientRepository.findAllByRecipeId(1L, Sort.by("id"))).thenReturn(ingredientSet);

        List<IngredientCommand> result = ingredientService.findAllRecipeIngredientsByRecipeId(1L);
        assertEquals(1, result.size());

        // BDD test
        verify(ingredientRepository, times(1)).findAllByRecipeId(1L, Sort.by("id"));
    }

    @Test
    void testGetIngredientById() throws NotFoundException {

        Optional<Ingredient> ingredientOptional = Optional.of(ingredient1);
        when(ingredientRepository.findById(any())).thenReturn(ingredientOptional);

        Ingredient result = ingredientService.getIngredientById(1L);

        assertNotNull(result);
        verify(ingredientRepository, times(1)).findById(anyLong());
        verify(ingredientRepository, never()).findAll();

    }

    @Test
    void testGetIngredientByIdThrowsException() {

        when(ingredientRepository.findById(any())).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NotFoundException.class, () -> ingredientService.getIngredientById(anyLong()));

        assertEquals("Ingredient not found", exception.getMessage());
    }

    @Test
    void testDeleteIngredientById() {
        ingredientRepository.deleteById(1L);
        verify(ingredientRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testSaveIngredientCommand() throws UnitOfMeasureNotFoundException, IngredientNotFoundException {

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        when(recipeRepository.save(any())).thenReturn(recipe);

        command.setId(3L)
        .setAmount(new BigDecimal(20))
        .setDescription("Avocado");

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(command);

        assertNotNull(savedIngredientCommand);

        assertEquals(3L, savedIngredientCommand.getId());
        assertEquals("Avocado", savedIngredientCommand.getDescription());
        assertEquals(new BigDecimal(20), savedIngredientCommand.getAmount());
        assertEquals(UOM_ID, savedIngredientCommand.getUom().getId());
        assertEquals(UOM_DESCRIPTION, savedIngredientCommand.getUom().getDescription());
        assertEquals(RECIPE_ID, savedIngredientCommand.getRecipeId());

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("Test create new ingredient")
    void createNewIngredient() throws UnitOfMeasureNotFoundException, IngredientNotFoundException {

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        recipe.getIngredients().removeIf(ingredient -> ingredient.getDescription().equalsIgnoreCase("rice"));

        when(recipeRepository.save(any())).thenReturn(recipe);

        IngredientCommand command2 = IngredientCommand.builder()
               .amount(new BigDecimal(20))
               .description("Avocado")
               .uom(unitOfMeasureToUnitOfMeasureCommand.convert(uom))
               .recipeId(1L)
               .build();

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(command2);

        assertNotNull(savedIngredientCommand);

        assertEquals(null, savedIngredientCommand.getId());
        assertEquals("Avocado", savedIngredientCommand.getDescription());
        assertEquals(new BigDecimal(20), savedIngredientCommand.getAmount());
        assertEquals(UOM_ID, savedIngredientCommand.getUom().getId());
        assertEquals(UOM_DESCRIPTION, savedIngredientCommand.getUom().getDescription());
        assertEquals(RECIPE_ID, savedIngredientCommand.getRecipeId());

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());

    }

    @Test
    void findIngredientCommandById() throws NotFoundException {

        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient1));

        IngredientCommand resultIngredientCommand = ingredientService.findIngredientCommandById(1L);

        assertNotNull(resultIngredientCommand);
        assertEquals(INGREDIENT_ID, resultIngredientCommand.getId());
        assertEquals(INGREDIENT_DESCRIPTION, resultIngredientCommand.getDescription());
        assertEquals(INGREDIENT_AMOUNT, resultIngredientCommand.getAmount());
        assertEquals(UOM_ID, resultIngredientCommand.getUom().getId());
        assertEquals(UOM_DESCRIPTION, resultIngredientCommand.getUom().getDescription());
    }


    @Test
    @Transactional
    void findRecipeIdAndRecipeIdPath() throws Exception {
        // given
        Recipe recipe = Recipe.builder().id(3L).build();

        recipe.addIngredient(ingredient1);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        // when

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(3L, 1L);
        verify(recipeRepository, times(1)).findById(anyLong());
        //then
        assertEquals(1L, ingredientCommand.getId());
        assertEquals(3L, ingredientCommand.getRecipeId());
    }

    @Test
    @Transactional
    void deleteIngredientById() throws Exception {
        // given
      ingredientService.deleteIngredientById(2L);
      verify(ingredientRepository, times(1)).deleteById(anyLong());
    }
}