package com.sbtraining.recipe_project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sousaJ on 07/01/2021
 * in package - com.sbtraining.recipe_project.model
 **/
@Entity
public class ImageModel {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
