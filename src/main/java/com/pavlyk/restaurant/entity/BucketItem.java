package com.pavlyk.restaurant.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class BucketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "bucket_id", referencedColumnName = "id")
    private Bucket bucket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;

    @Column(columnDefinition = "integer default 1")
    private Integer quantity;
}
