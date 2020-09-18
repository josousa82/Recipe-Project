package com.sbtraining.recipe_project.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by sousaJ on 16/09/2020
 * in package - com.sbtraining.recipe_project.model
 **/
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    private BigDecimal amount;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<UnitOfMeasure> uom;

    @ManyToOne
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Set<UnitOfMeasure> getUom() {
        return uom;
    }

    public void setUom(Set<UnitOfMeasure> uom) {
        this.uom = uom;
    }
}
