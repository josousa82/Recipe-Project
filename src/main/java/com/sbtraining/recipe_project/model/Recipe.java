package com.sbtraining.recipe_project.model;


import com.sbtraining.recipe_project.model.enums.Difficulty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sousaJ on 16/09/2020
 * in package - com.sbtraining.recipe_project.model
 **/

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Slf4j
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    private String source;

    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @Builder
    public Recipe(Long id, String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url, String directions, Difficulty difficulty, Notes notes) {
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.difficulty = difficulty;
        this.id = id;
        this.notes = notes;
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public Recipe addCategory(Category category) {
        if(Objects.isNull(category)) {
            log.error("Category cannot be null");
            throw new IllegalArgumentException("Category cannot be null");
        } else {
            this.categories.add(category);
            return this;
        }
    }

    public void setNotes(Notes notes) {
        if(Objects.nonNull(notes)){
            this.notes = notes;
            notes.setRecipe(this);
//            notes.setId(this.id);
        }
    }
}
