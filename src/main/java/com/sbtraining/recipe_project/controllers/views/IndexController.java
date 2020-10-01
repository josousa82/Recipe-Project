package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.repositories.CategoryRepository;
import com.sbtraining.recipe_project.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by sousaJ on 17/09/2020
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model){

        var optionalCategory = categoryRepository.findByCategoryName("Portuguese");
        var optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoon");

        System.out.println("optionalUnitOfMeasure.get().getDescription() = " + optionalUnitOfMeasure.get().getDescription());
        System.out.println("optionalCategory = " + optionalCategory.get().getCategoryName());

        var categories = StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        var unitOfMeasures =  StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        model.addAttribute("categories", categories );
        model.addAttribute("unitOfMeasures", unitOfMeasures);

        return "index";
    }
}
