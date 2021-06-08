package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.*;
import com.pavlyk.restaurant.repository.OrderRepository;
import com.pavlyk.restaurant.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    public final OrderRepository orderRepository;

    @Override
    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrderPaginated(Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("id").descending());
        Page<Order> orderList = orderRepository.findAll(pageable);
        if (orderList.hasContent()) {
            return orderList.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Order> getOrdersPaginated(Integer pageId, Integer pageSize, Long userId){
        Pageable pageable = PageRequest.of(pageId - 1, pageSize);
        Page<Order> orderPage = orderRepository.getOrdersByUserIdOrderByOrderDateDesc(userId,pageable);
        if(orderPage.hasContent()){
            return orderPage.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public void save(Order order){
        orderRepository.save(order);
    }

    @Override
    public Order mapBucketToOrder(Bucket bucket){
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setTotalPrice(bucket.getTotalPrice());
        order.setOrderItems(mapBucketItemsToOrderItems(bucket.getBucketItems(),order));
        return order;
    }

    @Override
    public int getPageCount(Integer pageId, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageId - 1, pageSize);
        Page<Order> pageResult = orderRepository.findAll(pageable);
        return pageResult.getTotalPages();
    }

    @Override
    public List<Order> getAllActiveOrders() {
       return orderRepository.getAllActiveOrders();
    }

    @Override
    public Integer getTotalPagesCount(Integer pageId, Integer pageSize,Long userId) {
        Pageable pageable = PageRequest.of(pageId - 1, pageSize);
        Page<Order> pageResult = orderRepository.getOrdersByUserIdOrderByOrderDateDesc(userId,pageable);
        return pageResult.getTotalPages();
    }

    private List<OrderItem> mapBucketItemsToOrderItems(Set<BucketItem> bucketItemSet,Order order){
        List<OrderItem> orderItemList = new ArrayList<>();
        for (BucketItem bucketItem : bucketItemSet) {
            OrderItem orderItem = new OrderItem();
            orderItem.setDish(bucketItem.getDish());
            orderItem.setQuantity(bucketItem.getQuantity());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

}
