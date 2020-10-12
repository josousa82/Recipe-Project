package com.sbtraining.recipe_project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by sousaJ on 16/09/2020
 * in package - com.sbtraining.recipe_project.model
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notes)) return false;

        Notes notes = (Notes) o;

        if (!Objects.equals(id, notes.id)) return false;
        if (!Objects.equals(recipe, notes.recipe)) return false;
        return recipeNotes.equals(notes.recipeNotes);
    }

}
