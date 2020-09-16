package com.sbtraining.recipe_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

/**
 * Created by sousaJ on 08/09/2020
 * in package - com.sbtraining.recipe_project.controllers
 **/
@Controller
@RequestMapping("/")
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest(Model model){
        model.addAttribute("test", "Teste");
        return Objects.requireNonNull(model.getAttribute("test")).toString();
    }
}
