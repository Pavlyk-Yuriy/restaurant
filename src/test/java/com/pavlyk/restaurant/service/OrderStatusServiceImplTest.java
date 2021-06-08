package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.OrderStatus;
import com.pavlyk.restaurant.repository.OrderStatusRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderStatusServiceImplTest {
    @Mock
    private OrderStatusRepository orderStatusRepository;

    @InjectMocks
    private OrderStatusServiceImpl orderStatusService;

    @Test
    public void getAllOrderStatus(){
        List<OrderStatus> expectedList = List.of(new OrderStatus(1,"delivering",new ArrayList<>()),
                new OrderStatus(2,"ordered",new ArrayList<>()));
        when(orderStatusRepository.findAll()).thenReturn(expectedList);
        List<OrderStatus> actualList = orderStatusService.getAllOrderStatus();
        Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void getOrderStatusByNameTest(){
        OrderStatus expected = new OrderStatus(1,"delivering",new ArrayList<>());
        when(orderStatusRepository.getOrderStatusByName("delivering")).thenReturn(expected);
        OrderStatus actual = orderStatusService.getOrderStatusByName("delivering");
        Assert.assertEquals(expected,actual);
    }
}
