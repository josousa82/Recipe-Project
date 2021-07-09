package com.sbtraining.recipe_project.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sousaJ on 16/09/2020
 * in package - com.sbtraining.recipe_project.model
 **/
@Data
@Builder
@EqualsAndHashCode(exclude = {"recipe"})
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;


    @ManyToOne
    @Builder.Default
    private Recipe recipe = new Recipe();


}
