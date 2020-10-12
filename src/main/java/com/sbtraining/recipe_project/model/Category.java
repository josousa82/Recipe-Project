package com.sbtraining.recipe_project.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
@Builder
@Entity
public class Category extends AbstractEntity {

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

//    public Long getId(){
//        return super.id;
//    }

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
