package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Bucket;
import com.pavlyk.restaurant.entity.Order;
import com.pavlyk.restaurant.entity.User;
import com.pavlyk.restaurant.repository.BucketRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BucketServiceImplTest {

    @Mock
    private BucketRepository bucketRepository;

    @InjectMocks
    private BucketServiceImpl bucketService;

    @Test
    public void saveBucketTest(){
        Bucket bucket = Bucket.builder()
                .createdDate(LocalDateTime.now())
                .totalPrice(0)
                .build();
        bucketService.saveBucket(bucket);
        verify(bucketRepository,times(1)).save(eq(bucket));
    }

    @Test
    public void getBucketByUserIdTest(){
        Bucket bucket = Bucket.builder()
                .id(1L)
                .createdDate(LocalDateTime.now())
                .totalPrice(0)
                .build();
        User user = new User(1L,"John","Smith","someEmail.@gmail.com",
                "password","098873445","address",
                "USER",bucket, Collections.singleton(new Order()),"password");
        when(bucketRepository.findById(user.getId())).thenReturn(Optional.of(bucket));
        Optional<Bucket> actual = bucketService.getBucketByUserId(user.getId());
        Assert.assertEquals(Optional.of(bucket),actual);
    }
}
