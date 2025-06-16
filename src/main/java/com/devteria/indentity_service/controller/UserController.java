package com.devteria.indentity_service.controller;

import com.devteria.indentity_service.dto.request.UserCreateRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.entity.User;
import com.devteria.indentity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody @Valid UserCreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{user_id}")
    User getUser(@PathVariable String user_id) {
        return userService.getUserByUserid(user_id.toString());
    }

    // Tìm kiếm bằng username (ví dụ: GET /users/username/john.doe)
    @GetMapping("/username/{user_name}")
    public User getUserByUsername(@PathVariable String user_name) {
        return userService.getUserByUsername(user_name); // Giả sử có phương thức getUserByUsername trong Service
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User deleted Successfully";
    }
}
