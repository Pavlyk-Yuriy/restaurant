package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Bucket;
import com.pavlyk.restaurant.entity.Order;
import com.pavlyk.restaurant.entity.User;
import com.pavlyk.restaurant.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private final User ACTUAL_USER = new User(1L,"John","Smith","someEmail.@gmail.com",
            "password","098873445","address",
            "USER",new Bucket(), Collections.singleton(new Order()),"password");

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void getUserByEmailTest(){
        when(userRepository.getUserByEmail("someEmail.@gmail.com")).thenReturn(ACTUAL_USER);
        User currentUser = userService.getUserByEmail("someEmail.@gmail.com");
        Assert.assertEquals(ACTUAL_USER,currentUser);
    }

    @Test
    public void getUserByIdTest(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(ACTUAL_USER));
        Optional<User> expected = userService.getUserById(1L);
        Assert.assertEquals(Optional.of(ACTUAL_USER),expected);
    }

    @Test
    public void updateUserTest(){
        userService.updateUser(ACTUAL_USER);
        verify(userRepository,times(1)).save(eq(ACTUAL_USER));
    }

    @Test
    public void saveUserExpectedFalseTest(){
        when(userRepository.getUserByEmail("someEmail.@gmail.com")).thenReturn(ACTUAL_USER);
        boolean expected = userService.saveUser(ACTUAL_USER);
        Assert.assertFalse(expected);
    }
}
