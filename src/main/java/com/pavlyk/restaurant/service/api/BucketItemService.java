package com.pavlyk.restaurant.service.api;

import com.pavlyk.restaurant.entity.BucketItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BucketItemService {
    void save(BucketItem bucketItem);

    void delete(BucketItem bucketItem);

    BucketItem getBucketItemByDishIdAndAndBucketId(Long dishId, Long bucketId);

    void deleteAllBucketItemByBucketId(Long bucketId);
}
