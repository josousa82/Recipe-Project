package com.sbtraining.recipe_project.model;

import javax.persistence.*;

/**
 * Created by sousaJ on 16/09/2020
 * in package - com.sbtraining.recipe_project.model
 **/

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String notes) {
        this.recipeNotes = notes;
    }
}
