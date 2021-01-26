package com.sbtraining.recipe_project.cucumber;

import com.sbtraining.recipe_project.RecipeProjectApplication;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


/**
 * Created by sousaJ on 14/01/2021
 * in package - com.sbtraining.recipe_project.cucumber
 **/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = RecipeProjectApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringContextConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);

    @BeforeEach
    void setUp() {
        LOG.info(() -> "cucumber tests");
    }
}
