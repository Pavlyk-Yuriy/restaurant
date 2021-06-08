package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.OrderStatus;
import com.pavlyk.restaurant.repository.OrderStatusRepository;
import com.pavlyk.restaurant.service.api.OrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        return (List<OrderStatus>) orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus getOrderStatusByName(String name){
        return orderStatusRepository.getOrderStatusByName(name);
    }
}
