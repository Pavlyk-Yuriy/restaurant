package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Category;
import com.pavlyk.restaurant.repository.CategoryRepository;
import com.pavlyk.restaurant.service.api.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory(){
       return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name){
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public void save(Category category){
        categoryRepository.save(category);
    }
}
