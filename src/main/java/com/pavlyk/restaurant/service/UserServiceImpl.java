package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.User;
import com.pavlyk.restaurant.repository.UserRepository;
import com.pavlyk.restaurant.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    
    @Override
    public boolean saveUser(User user){
        User userFromDB = userRepository.getUserByEmail(user.getEmail());
        if (userFromDB != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(User user){
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String name) {
        return userRepository.getUserByEmail(name);
    }
}
