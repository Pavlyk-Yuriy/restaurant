package com.pavlyk.restaurant.service.api;

import com.pavlyk.restaurant.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category getCategoryByName(String name);

    void save(Category category);
}
