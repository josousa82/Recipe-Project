package com.sbtraining.recipe_project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;


    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(Objects.isNull(o)) return false;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        if (!Objects.equals(this.id, category.id)) return false;
        if (!Objects.equals(description, category.description)) return false;
        return recipes.equals(category.recipes);
    }

}
