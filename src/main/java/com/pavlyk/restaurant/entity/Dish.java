package com.pavlyk.restaurant.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "cooking_time", nullable = false)
    private LocalTime cookingTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private Long quantity;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "dish_ingredient",
            joinColumns = {@JoinColumn(name = "dish_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    Set<Ingredient> ingredientSet;

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY)
    Set<BucketItem> bucketItems;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;
}
