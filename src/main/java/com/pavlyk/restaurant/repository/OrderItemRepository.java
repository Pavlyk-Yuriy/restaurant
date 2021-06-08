package com.pavlyk.restaurant.repository;

import com.pavlyk.restaurant.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
