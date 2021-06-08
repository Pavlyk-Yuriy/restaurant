package com.pavlyk.restaurant.repository;

import com.pavlyk.restaurant.entity.BucketItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BucketItemRepository extends CrudRepository<BucketItem, Long> {
    List<BucketItem> getBucketItemByBucketId(Long id);
    BucketItem getBucketItemByDishId(Long id);
    BucketItem getBucketItemByDishIdAndAndBucketId(Long dishId, Long bucketId);

    @Transactional
    void deleteBucketItemByDishId(Long id);

    @Transactional
    void deleteBucketItemsByBucketId(Long id);

    @Modifying
    @Query("delete from BucketItem b where b.bucket.id=:bucketId")
    @Transactional
    void deleteAllBucketItemByBucketId(Long bucketId);
}
