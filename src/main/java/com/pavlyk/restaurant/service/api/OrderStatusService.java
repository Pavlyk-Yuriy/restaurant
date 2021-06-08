package com.pavlyk.restaurant.service.api;

import com.pavlyk.restaurant.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatus> getAllOrderStatus();

    OrderStatus getOrderStatusByName(String name);
}
