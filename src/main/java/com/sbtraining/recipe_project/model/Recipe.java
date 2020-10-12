package com.sbtraining.recipe_project.model;


import com.sbtraining.recipe_project.model.enums.Difficulty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sousaJ on 16/09/2020
 * in package - com.sbtraining.recipe_project.model
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ToString.Exclude
    private Integer prepTime;

    @ToString.Exclude
    private Integer cookTime;

    @ToString.Exclude
    private Integer servings;

    @ToString.Exclude
    private String source;

    @ToString.Exclude
    private String url;

    @ToString.Exclude
    @Lob
    private String directions;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @ToString.Exclude
    @Lob
    private Byte[] image;

    @ToString.Exclude
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;


    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void addCategory(Category category) {
        if(Objects.isNull(category)) {
            log.error("Category cannot be null");
            throw new IllegalArgumentException("Category cannot be null");
        } else {
            this.categories.add(category);
        }
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setId(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;

        Recipe recipe = (Recipe) o;

        if (!Objects.equals(id, recipe.id)) return false;
        if (!Objects.equals(description, recipe.description)) return false;
        if (!Objects.equals(prepTime, recipe.prepTime)) return false;
        if (!Objects.equals(cookTime, recipe.cookTime)) return false;
        if (!Objects.equals(servings, recipe.servings)) return false;
        if (!Objects.equals(source, recipe.source)) return false;
        if (!Objects.equals(url, recipe.url)) return false;
        if (!Objects.equals(directions, recipe.directions)) return false;
        if (!Objects.equals(ingredients, recipe.ingredients)) return false;
        if (!Objects.equals(categories, recipe.categories)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(image, recipe.image)) return false;
        if (difficulty != recipe.difficulty) return false;
        return notes.equals(recipe.notes);
    }


}
