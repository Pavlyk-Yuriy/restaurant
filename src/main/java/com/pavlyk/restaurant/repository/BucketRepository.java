package com.pavlyk.restaurant.repository;

import com.pavlyk.restaurant.entity.Bucket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BucketRepository extends CrudRepository<Bucket, Long> {
    @Modifying
    @Query(value = "DELETE FROM Bucket b WHERE b.id =:id", nativeQuery = true)
    @Transactional
    void deleteBucket(Long id);
}
