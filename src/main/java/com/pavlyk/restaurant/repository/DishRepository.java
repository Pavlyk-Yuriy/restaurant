package com.pavlyk.restaurant.repository;

import com.pavlyk.restaurant.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DishRepository extends CrudRepository<Dish, Long>, JpaRepository<Dish, Long>,
        PagingAndSortingRepository<Dish, Long> {

    Page<Dish> getDishesByCategoryNameAndDeletedIsFalse(String category, Pageable pageable);
    Page<Dish> getAllByDeletedIsFalse(Pageable pageable);
    Page<Dish> getDishesByCategoryName(String category, Pageable pageable);
}
