package com.pavlyk.restaurant.repository;

import com.pavlyk.restaurant.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByEmail(String email);
}
