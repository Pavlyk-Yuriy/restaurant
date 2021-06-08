package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Bucket;
import com.pavlyk.restaurant.repository.BucketRepository;
import com.pavlyk.restaurant.service.api.BucketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;

    @Override
    public Optional<Bucket> getBucketByUserId(Long id){
        return bucketRepository.findById(id);
    }

    @Override
    public void saveBucket(Bucket bucket){
        bucketRepository.save(bucket);
    }
}
