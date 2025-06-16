package com.devteria.indentity_service.service;

import com.devteria.indentity_service.dto.request.UserCreateRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.entity.User;
import com.devteria.indentity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreateRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserid(String user_id) {
        return userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found")) ;

    }

    public User getUserByUsername(String username) {
        // Sử dụng phương thức findByUsername mới
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public User updateUser(String user_id , UserUpdateRequest request) {
        User user = getUserByUserid(user_id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        return userRepository.save(user);
    }

    public void deleteUser(String user_id) {
        userRepository.deleteById(user_id);
    }


}
