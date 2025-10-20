package com.store.ecommerce.controller;

import com.store.ecommerce.DTOs.User.UserRequest;
import com.store.ecommerce.DTOs.User.UserResponse;
import com.store.ecommerce.service.UserService;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/userapi")
public class UserController {
    private final UserService userService;

    public UserController(UserService _userService){
        this.userService = _userService;
    }

    @GetMapping("/getallUsers")
    public List<UserResponse> GetUsers() {
        return userService.allUsers();
    }

    @GetMapping("/getUserById/{id}")
    public UserResponse GetUser(@PathVariable Long id) {
        return userService.FindUserById(id);
    }

    @PostMapping("/add")
    public UserResponse postMethodName(@RequestBody UserRequest user) {
        return userService.RegisterUser(user);
    }

    @PutMapping("edit/{id}")
    public UserRequest putMethodName(@PathVariable Long id, @RequestBody UserRequest entity) {
        userService.EditUser(id, entity);
        return entity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.DeleteUserById(id);
        // Returns HTTP Status 204 (No Content)
        return ResponseEntity.noContent().build();
    }
    
    
}
