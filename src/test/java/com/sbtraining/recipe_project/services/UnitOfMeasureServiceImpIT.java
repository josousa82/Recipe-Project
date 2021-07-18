package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import com.sbtraining.recipe_project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.sbtraining.recipe_project.model.UnitOfMeasure;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImpIT {

    UnitOfMeasureToUnitOfMeasureCommand command = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    UnitOfMeasure unitOfMeasure1;
    UnitOfMeasure unitOfMeasure2;

    @Mock
    UnitOfMeasureRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        unitOfMeasure1 = UnitOfMeasure.builder()
                .id(1L)
                .description("Teaspoon")
                .build();

        unitOfMeasure2 = UnitOfMeasure.builder()
                .id(2L)
                .description("Each")
                .build();

        service = new UnitOfMeasureServiceImpl(repository, command);
    }

    @Test
    void listAllUoms() {
        Set<UnitOfMeasure> uomSet = Set.of(unitOfMeasure1, unitOfMeasure2);
        when(repository.findAll()).thenReturn(uomSet);

        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        assertEquals(2, commands.size());
        verify(repository, times(1)).findAll();
    }
}