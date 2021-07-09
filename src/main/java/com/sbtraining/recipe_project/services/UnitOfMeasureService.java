package com.sbtraining.recipe_project.services;

import com.sbtraining.recipe_project.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by sousaJ on 16/12/2020
 * in package - com.sbtraining.recipe_project.services
 **/
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
