package com.pavlyk.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Formula(value = "(select sum(bucket_item.quantity * dish.price)  from Bucket b\n" +
            "join Bucket_Item bucket_item on bucket_item.bucket_id = b.id\n" +
            "join Dish dish on  dish.id = bucket_item.dish_id\n" +
            "where b.id = id)")
    private Integer totalPrice;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonManagedReference
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "bucket")
    @OrderBy("quantity")
    Set<BucketItem> bucketItems;

    public Bucket(LocalDateTime createdDate, Integer totalPrice, User user) {
        this.createdDate = createdDate;
        this.totalPrice = totalPrice;
        this.user = user;
    }
}
