package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.IngredientCommand;
import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import com.sbtraining.recipe_project.converters.IngredientCommandToIngredient;
import com.sbtraining.recipe_project.converters.IngredientToIngredientCommand;
import com.sbtraining.recipe_project.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.sbtraining.recipe_project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.sbtraining.recipe_project.model.Ingredient;
import com.sbtraining.recipe_project.model.UnitOfMeasure;
import com.sbtraining.recipe_project.repositories.IngredientRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    private final Long INGREDIENT_ID = 1L;
    private final String INGREDIENT_DESCRIPTION = "Rice";
    private final String UOM_DESCRIPTION = "Teaspoon";
    private final BigDecimal INGREDIENT_AMOUNT = BigDecimal.valueOf(30L);
    private final Long UOM_ID = 1L;



    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;


    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    private UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter;
    private IngredientCommand command;
    private IngredientService ingredientService;
    private Ingredient ingredient1;
    private Set<Ingredient> ingredientSet;

    @BeforeEach
    void setUp() {

        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
        uomCommandConverter = new UnitOfMeasureToUnitOfMeasureCommand();

        ingredientCommandToIngredient = new IngredientCommandToIngredient(uomConverter);
        ingredientToIngredientCommand = new IngredientToIngredientCommand(uomCommandConverter);

        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientRepository,
                ingredientCommandToIngredient, ingredientToIngredientCommand);

        ingredient1 = Ingredient.builder()
                .id(INGREDIENT_ID)
                .description(INGREDIENT_DESCRIPTION)
                .amount(INGREDIENT_AMOUNT)
                .uom(UnitOfMeasure.builder()
                        .id(UOM_ID)
                        .description(UOM_DESCRIPTION)
                        .build())
                .build();

        ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient1);

        command = IngredientCommand.builder()
                .id(INGREDIENT_ID)
                .description(INGREDIENT_DESCRIPTION)
                .amount(INGREDIENT_AMOUNT)
                .uom(UnitOfMeasureCommand.builder()
                        .id(UOM_ID)
                        .description(UOM_DESCRIPTION)
                        .build())
                .build();

    }

    @Test
    void testGetAllIngredients() {

        when(ingredientRepository.findAll()).thenReturn(ingredientSet);
        List<Ingredient> result = ingredientService.getAllIngredients();
        assertEquals(1, result.size());

        // BDD test
        verify(ingredientRepository, times(1)).findAll();
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

        Throwable exception = assertThrows(NotFoundException.class,
                () -> ingredientService.getIngredientById(anyLong()));

        assertEquals("Ingredient not found", exception.getMessage());
    }

    @Test
    void testDeleteIngredientById() {
        ingredientRepository.deleteById(1L);
        verify(ingredientRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testSaveIngredientCommand() {

        when(ingredientRepository.save(ingredient1)).thenReturn(ingredient1);
        when(ingredientCommandToIngredient.convert(command)).thenReturn(ingredient1);
        when(ingredientToIngredientCommand.convert(ingredient1)).thenReturn(command);

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(command);

        assertNotNull(savedIngredientCommand);

        assertEquals(INGREDIENT_ID, savedIngredientCommand.getId());
        assertEquals(INGREDIENT_DESCRIPTION, savedIngredientCommand.getDescription());
        assertEquals(INGREDIENT_AMOUNT, savedIngredientCommand.getAmount());
        assertEquals(UOM_ID, savedIngredientCommand.getUom().getId());
        assertEquals(UOM_DESCRIPTION, savedIngredientCommand.getUom().getDescription());

    }

    @Test
    void findIngredientCommandById() throws NotFoundException {

        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient1));
        when(ingredientToIngredientCommand.convert(ingredient1)).thenReturn(command);

        IngredientCommand resultIngredientCommand = ingredientService.findIngredientCommandById(1L);

        assertNotNull(resultIngredientCommand);
        assertEquals(INGREDIENT_ID, resultIngredientCommand.getId());
        assertEquals(INGREDIENT_DESCRIPTION, resultIngredientCommand.getDescription());
        assertEquals(INGREDIENT_AMOUNT, resultIngredientCommand.getAmount());
        assertEquals(UOM_ID, resultIngredientCommand.getUom().getId());
        assertEquals(UOM_DESCRIPTION, resultIngredientCommand.getUom().getDescription());
    }
}