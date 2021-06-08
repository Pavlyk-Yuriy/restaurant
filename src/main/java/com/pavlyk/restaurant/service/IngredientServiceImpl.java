package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Ingredient;
import com.pavlyk.restaurant.repository.IngredientRepository;
import com.pavlyk.restaurant.service.api.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    @Override
    public List<Ingredient> getAllIngredients(){
        return (List<Ingredient>) ingredientRepository.findAll();
    }

    @Override
    public void save(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }
}
