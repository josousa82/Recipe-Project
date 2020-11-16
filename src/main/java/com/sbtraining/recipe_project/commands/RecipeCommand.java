package com.sbtraining.recipe_project.commands;

import com.sbtraining.recipe_project.model.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sousaJ on 08/11/2020
 * in package - com.sbtraining.recipe_project.commands
 **/

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredientsC = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NotesCommand notesC;
    private Set<CategoryCommand> categoriesC = new HashSet<>();

    public Long getId() {
        return id;
    }

    public RecipeCommand setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeCommand setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeCommand setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeCommand setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public Integer getServings() {
        return servings;
    }

    public RecipeCommand setServings(Integer servings) {
        this.servings = servings;
        return this;
    }

    public String getSource() {
        return source;
    }

    public RecipeCommand setSource(String source) {
        this.source = source;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RecipeCommand setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDirections() {
        return directions;
    }

    public RecipeCommand setDirections(String directions) {
        this.directions = directions;
        return this;
    }

    public Set<IngredientCommand> getIngredientsC() {
        return ingredientsC;
    }

    public RecipeCommand setIngredientsC(Set<IngredientCommand> ingredientsC) {
        this.ingredientsC = ingredientsC;
        return this;
    }

    public Byte[] getImage() {
        return image;
    }

    public RecipeCommand setImage(Byte[] image) {
        this.image = image;
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public RecipeCommand setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public NotesCommand getNotesC() {
        return notesC;
    }

    public RecipeCommand setNotesC(NotesCommand notesC) {
        this.notesC = notesC;
        return this;
    }

    public Set<CategoryCommand> getCategoriesC() {
        return categoriesC;
    }

    public RecipeCommand setCategoriesC(Set<CategoryCommand> categoriesC) {
        this.categoriesC = categoriesC;
        return this;
    }
}
