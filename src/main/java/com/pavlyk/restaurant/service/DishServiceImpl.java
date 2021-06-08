package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Dish;
import com.pavlyk.restaurant.repository.DishRepository;
import com.pavlyk.restaurant.service.api.DishService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    @Override
    public void save(Dish dish){
        dishRepository.save(dish);
    }

    @Override
    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }
    @Override
    public List<Dish> getAllDishesPaginated(Integer pageNumber, Integer pageSize,
                                            String sortBy, String category, boolean admin) {
        Pageable pageable;
        if (sortBy.equals("priceAsc")) {
            pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("price").ascending());
        } else if (sortBy.equals("priceDesc")) {
            pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("price").descending());
        } else {
            pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sortBy));
        }
        Page<Dish> pageResult = getDishes(category, admin, pageable);
        if (pageResult.hasContent()) {
            return pageResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Integer getPageCount(Integer pageNumber, Integer pageSize, String category, boolean admin) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Dish> pageResult = getDishes(category, admin, pageable);
        return pageResult.getTotalPages();
    }

    private Page<Dish> getDishes(String category, boolean admin, Pageable pageable) {
        Page<Dish> pageResult;
        if (category != null && !category.isEmpty() && admin) {
            pageResult = dishRepository.getDishesByCategoryName(category, pageable);
        } else if (category != null && !category.isEmpty()) {
            pageResult = dishRepository.getDishesByCategoryNameAndDeletedIsFalse(category, pageable);
        } else if (admin) {
            pageResult = dishRepository.findAll(pageable);
        } else {
            pageResult = dishRepository.getAllByDeletedIsFalse(pageable);
        }
        return pageResult;
    }
}
