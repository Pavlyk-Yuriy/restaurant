package com.pavlyk.restaurant.service.api;

import com.pavlyk.restaurant.entity.Bucket;

import java.util.Optional;

public interface BucketService {
    Optional<Bucket> getBucketByUserId(Long id);

    void saveBucket(Bucket bucket);
}
