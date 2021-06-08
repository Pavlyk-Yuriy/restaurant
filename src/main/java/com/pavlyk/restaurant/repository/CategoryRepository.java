package com.pavlyk.restaurant.repository;

import com.pavlyk.restaurant.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category getCategoryByName(String name);
}
