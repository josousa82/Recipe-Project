package com.sbtraining.recipe_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sousaJ on 08/09/2020
 * in package - com.sbtraining.recipe_project.controllers
 **/
@Controller
@RequestMapping("/")
public class TestController {

    @RequestMapping("/test")
    public String getTest(Model model){
        return "teste";
    }
}
