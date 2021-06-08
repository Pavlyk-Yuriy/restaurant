package com.pavlyk.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "payment_status",columnDefinition="tinyint(1) default 0")
    boolean paymentStatus;
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;
    @Column(name = "delivery_address")
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "order")
    @OrderBy("quantity")
    List<OrderItem> orderItems;
}
