package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.Ingredient;
import com.pavlyk.restaurant.service.api.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class IngredientsController {
    private final IngredientService ingredientService;

    @PostMapping("/saveIngredient")
    public String saveCategory(@ModelAttribute("ingredientModel") Ingredient ingredient){
        ingredientService.save(ingredient);
        return "redirect:/index";
    }
}
