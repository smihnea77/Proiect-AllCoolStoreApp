package com.allcoolstore.controller;

import com.allcoolstore.model.User;
import com.allcoolstore.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/create-user")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping(path = "/update-user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
