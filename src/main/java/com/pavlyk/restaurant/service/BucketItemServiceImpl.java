package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.BucketItem;
import com.pavlyk.restaurant.repository.BucketItemRepository;
import com.pavlyk.restaurant.service.api.BucketItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BucketItemServiceImpl implements BucketItemService {

    private final BucketItemRepository bucketItemRepository;
    @Override
    public void save(BucketItem bucketItem){
        bucketItemRepository.save(bucketItem);
    }

    @Override
    public void delete(BucketItem bucketItem){
        bucketItemRepository.delete(bucketItem);
    }

    @Override
    public BucketItem getBucketItemByDishIdAndAndBucketId(Long dishId, Long bucketId) {
        return bucketItemRepository.getBucketItemByDishIdAndAndBucketId(dishId,bucketId);
    }

    @Override
    public void deleteAllBucketItemByBucketId(Long bucketId){
        bucketItemRepository.deleteAllBucketItemByBucketId(bucketId);
    }
}
