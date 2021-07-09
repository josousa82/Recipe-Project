package com.sbtraining.recipe_project.utils;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by sousaJ on 07/02/2021
 * in package - com.sbtraining.recipe_project.utils
 **/
public class Filters {

    // Filters repeated elements, alternative to distinct that olds state
    public static <T>Predicate<T> distinctKey(Function<? super T, ?> keyExtractor){
        Set<Object> addedElementsSet = ConcurrentHashMap.newKeySet();
        return t -> addedElementsSet.add(keyExtractor.apply(t));
    }
}
