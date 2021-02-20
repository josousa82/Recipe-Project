package com.sbtraining.recipe_project.controllers.views;

import com.sbtraining.recipe_project.commands.RecipeCommand;
import com.sbtraining.recipe_project.services.ImageService;
import com.sbtraining.recipe_project.services.RecipeService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by sousaJ on 02/01/2021
 * in package - com.sbtraining.recipe_project.controllers.views
 **/
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;
    private final String MODEL_MSG = "message" ;


    @GetMapping("/recipe/{recipeId}/image/uploadImage")
    public String getUploadForm(@PathVariable String recipeId, Model model) throws NotFoundException {
        RecipeCommand command = recipeService.findCommandById(Long.parseLong(recipeId));
        model.addAttribute("recipe", command);
        String some = (String) model.asMap().get(MODEL_MSG);
        model.addAttribute(MODEL_MSG, some);
        log.info("Recipe to upload image wit id = {}", command.getId());
        return "image-upload";
    }

    @PostMapping("/recipe/{id}/image/uploadImage")
    public String saveImage(@RequestParam("image") MultipartFile image, RedirectAttributes attributes, @PathVariable String id)  {
        if (Objects.isNull(image)) {
            attributes.addFlashAttribute(MODEL_MSG, "Please select a file to upload.");
            return "redirect:/recipe/" + id + "/image/uploadForm/";
        } else {

            imageService.saveImageFile(command, image);
            attributes.addFlashAttribute(MODEL_MSG, "You successfully uploaded " + image.getOriginalFilename() + '!');
            return ViewsLinksEnum.RECIPE_FORM.getLink();
        }
    }

    @GetMapping("/recipe/{id}/get/image/")
    public void getImageFromDB(@PathVariable String id, HttpServletResponse response){
          response = imageService.getRecipeImage(Long.valueOf(id), response);
    }
}
