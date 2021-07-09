package com.sbtraining.recipe_project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by sousaJ on 08/09/2020
 * in package - com.sbtraining.recipe_project.configuration
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig  {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Recipe Project")
                .description("Spring boot Api for a Recipe Project, part of a tutorial")
                .version("0.0.1")
                .license("MIT License")
                .licenseUrl("LICENSE")
                .build();
    }

}
