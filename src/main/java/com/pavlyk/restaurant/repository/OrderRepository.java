package com.pavlyk.restaurant.repository;

import com.pavlyk.restaurant.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>,
        PagingAndSortingRepository<Order, Long> {
    List<Order> getOrdersByUserId(Long userId);

    @Query(nativeQuery = true, value = "select * from restaurant.orders o left OUTER JOIN restaurant.order_status os on os.id = o.status_id\n" +
            "            where o.status_id is null or os.name <> 'delivering' or o.payment_status = false;")
    List<Order> getAllActiveOrders();

    Page<Order> getOrdersByUserIdOrderByOrderDateDesc(Long userId, Pageable pageable);
}
