package com.sbtraining.recipe_project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 05/11/2020
 * in package - com.sbtraining.recipe_project.configuration
 **/
@Component
@PropertySource("classpath:boostrap-data.properties")
public class ConfigDataProperties {

    private final Environment env;

    @Autowired
    public ConfigDataProperties(Environment env) {
        this.env = env;
    }

    public String getConfigValue(String configKey){
        return env.getProperty(configKey);
    }
}
