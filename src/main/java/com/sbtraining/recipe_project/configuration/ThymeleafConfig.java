package com.sbtraining.recipe_project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.thymeleaf.spring5.view.ThymeleafView;

/**
 * Created by sousaJ on 17/02/2021
 * in package - com.sbtraining.recipe_project.configuration
 **/
@Configuration
public class ThymeleafConfig {

    @Bean(name = "image-upload")
    @Scope("prototype")
    public ThymeleafView imageFragmentView(){
        ThymeleafView imageUploaderView = new ThymeleafView("recipeImageModalForm");
        imageUploaderView.setMarkupSelector("recipeImageModal-fragment");
        return imageUploaderView;
    }


}
