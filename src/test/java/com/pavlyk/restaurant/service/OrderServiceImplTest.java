package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Order;
import com.pavlyk.restaurant.entity.OrderStatus;
import com.pavlyk.restaurant.entity.User;
import com.pavlyk.restaurant.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void getOrderByIdTest(){
        Order expected = new Order(1L,true,new Date(),400,"address",
                new OrderStatus(),new User(),new ArrayList<>());
        when(orderRepository.findById(1L)).thenReturn(Optional.of(expected));
        Optional<Order> actual = orderService.getOrderById(1L);
        Assert.assertEquals(Optional.of(expected),actual);
    }

    @Test
    public void saveOrderTest(){
        Order order = new Order(1L,true,new Date(),400,"address",
                new OrderStatus(),new User(),new ArrayList<>());
        orderService.save(order);
        verify(orderRepository,times(1)).save(eq(order));
    }

}
