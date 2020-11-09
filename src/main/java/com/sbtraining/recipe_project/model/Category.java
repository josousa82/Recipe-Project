package com.sbtraining.recipe_project.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sousaJ on 16/09/2020
 * in package - com.sbtraining.recipe_project.model
 **/
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Builder
    public Category(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;


}
