package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.model.UnitOfMeasure;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sousaJ on 16/12/2020
 * in package - com.sbtraining.recipe_project.services
 **/
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public List<UnitOfMeasure> listAllUoms() {
        List<UnitOfMeasure> uomList = new ArrayList<>();
        Iterable<UnitOfMeasure> unitOfMeasures =  unitOfMeasureRepository.findAll();
        unitOfMeasures.iterator().forEachRemaining(uomList::add);
        return uomList;
    }
}
