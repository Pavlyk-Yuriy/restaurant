package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.Category;
import com.pavlyk.restaurant.entity.Dish;
import com.pavlyk.restaurant.entity.Ingredient;
import com.pavlyk.restaurant.service.api.CategoryService;
import com.pavlyk.restaurant.service.api.DishService;
import com.pavlyk.restaurant.service.api.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class DishController {

    private final CategoryService categoryService;
    private final DishService dishService;
    private final IngredientService ingredientService;

    @GetMapping("/addDish")
    public String addDishToDB(ModelMap modelMap){
        modelMap.put("categoryList",categoryService.getAllCategory());
        modelMap.addAttribute("dishModel", new Dish());
        modelMap.addAttribute("categoryModel", new Category());
        modelMap.addAttribute("ingredientModel", new Ingredient());
        modelMap.put("ingredientSet", ingredientService.getAllIngredients());
        return "addDish";
    }

    @PostMapping("/saveDish")
    public String saveDishToDB(@ModelAttribute("dishModel") Dish dish){
        dish.setCategory(categoryService.getCategoryByName(dish.getCategory().getName()));
        dishService.save(dish);
        return "redirect:/index";
    }

    @GetMapping("/editDish")
    public String editDish(ModelMap modelMap, @RequestParam Long dishId){
        Dish dish = dishService.getDishById(dishId).get();
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        ingredients.removeAll(dish.getIngredientSet());
        modelMap.addAttribute("dishModel",new Dish());
        modelMap.put("dish", dish);
        modelMap.put("ingredientSet", ingredientService.getAllIngredients());
        modelMap.put("ingredientRemoved",ingredients);
        modelMap.put("categoryList", categoryService.getAllCategory());
        return "editDish";
    }

    @PostMapping("/editDish")
    public String editDish(@ModelAttribute("dishModel") Dish dish){
        dish.setCategory(categoryService.getCategoryByName(dish.getCategory().getName()));
        dishService.save(dish);
        return "redirect:/index";
    }
}
