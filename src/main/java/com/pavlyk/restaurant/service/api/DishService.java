package com.pavlyk.restaurant.service.api;

import com.pavlyk.restaurant.entity.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {

    void save(Dish dish);

    Optional<Dish> getDishById(Long id);

    List<Dish> getAllDishes();

    List<Dish> getAllDishesPaginated(Integer pageNumber, Integer pageSize, String sortBy, String category, boolean admin);

    Integer getPageCount(Integer pageNumber, Integer pageSize,String category, boolean admin);

}
