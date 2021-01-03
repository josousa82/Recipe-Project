package com.sbtraining.recipe_project.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by sousaJ on 03/01/2021
 * in package - com.sbtraining.recipe_project.model
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@Table(name = "images_table")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String type;

    private Byte[] imageBytes;

    @OneToOne
    private Recipe recipe;

}
