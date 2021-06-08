package com.pavlyk.restaurant.service.api;

import com.pavlyk.restaurant.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngredients();

    void save(Ingredient ingredient);
}
