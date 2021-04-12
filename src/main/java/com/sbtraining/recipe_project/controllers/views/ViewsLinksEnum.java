package com.sbtraining.recipe_project.controllers.views;

/**
 * Created by sousaJ on 31/01/2021
 * in package - com.sbtraining.recipe_project.utils
 **/

public enum ViewsLinksEnum {

    RECIPE_FORM ("backend-ui/pages/recipeFormUpdateCreate"),
//    RECIPE_FORM ("recipe/recipeForm"),
    RECIPE_REDIRECT_ALL ("redirect:/"),
    RECIPE_SHOW ("frontend-ui/pages/recipe"),
    RECIPE_REDIRECT("redirect:/recipe/"),
    INDEX_HOME("frontend-ui/pages/home");

    private final String value;

    ViewsLinksEnum(String s) {
        this.value = s;
    }

    public String getLink() {
        return value;
    }
}
