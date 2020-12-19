package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;
import com.sbtraining.recipe_project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by sousaJ on 16/12/2020
 * in package - com.sbtraining.recipe_project.services
 **/
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand uomconverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand uomconverter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.uomconverter = uomconverter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(uomconverter::convert)
                .collect(Collectors.toSet());

    }
}
