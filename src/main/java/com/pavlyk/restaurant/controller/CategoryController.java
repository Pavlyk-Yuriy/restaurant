package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.Category;
import com.pavlyk.restaurant.service.api.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("categoryModel")Category category){
        categoryService.save(category);
        return "redirect:/index";
    }
}
