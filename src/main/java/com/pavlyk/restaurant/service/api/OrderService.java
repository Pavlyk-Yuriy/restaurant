package com.pavlyk.restaurant.service.api;

import com.pavlyk.restaurant.entity.Bucket;
import com.pavlyk.restaurant.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getOrderById(Long id);

    List<Order> getAllOrderPaginated(Integer pageNumber, Integer pageSize);

    List<Order> getOrdersPaginated(Integer pageId, Integer pageSize, Long userId);

    void save(Order order);

    Order mapBucketToOrder(Bucket bucket);

    int getPageCount(Integer pageId, Integer pageSize);

    List<Order> getAllActiveOrders();

    Integer getTotalPagesCount(Integer pageId, Integer pageSize,Long userId);
}
