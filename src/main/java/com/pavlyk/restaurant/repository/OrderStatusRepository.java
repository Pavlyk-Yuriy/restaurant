package com.pavlyk.restaurant.repository;

import com.pavlyk.restaurant.entity.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus,Long> {
    OrderStatus getOrderStatusByName(String name);
}
