package com.pavlyk.restaurant.service.api;

import com.pavlyk.restaurant.entity.User;

import java.util.Optional;

public interface UserService {
     boolean saveUser(User user);

    Optional<User> getUserById(Long id);

    void updateUser(User user);

    User getUserByEmail(String name);
}
