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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
@Entity
public class Recipe extends AbstractEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

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

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public void addCategory(Category category) {
        if (Objects.isNull(category)){
            log.error("Category cannot be null");
            throw new RuntimeException("Category cannot be null");
        }else{
            this.categories.add(category);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        if (!super.equals(o)) return false;
        Recipe recipe = (Recipe) o;

        if (!Objects.equals(description, recipe.description)) return false;
        if (!Objects.equals(prepTime, recipe.prepTime)) return false;
        if (!Objects.equals(cookTime, recipe.cookTime)) return false;
        if (!Objects.equals(servings, recipe.servings)) return false;
        if (!Objects.equals(source, recipe.source)) return false;
        if (!Objects.equals(url, recipe.url)) return false;
        if (!Objects.equals(directions, recipe.directions)) return false;
        if (!Objects.equals(ingredients, recipe.ingredients)) return false;
        if (!Objects.equals(categories, recipe.categories)) return false;
//        // Probably incorrect - comparing Object[] arrays with Arrays.equals
//        if (!Arrays.equals(image, recipe.image)) return false;
        if (difficulty != recipe.difficulty) return false;
        return notes.equals(recipe.notes);
    }

}
