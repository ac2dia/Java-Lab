package io.springfox.springfoxswagger2.controller;

import io.springfox.springfoxswagger2.domain.User;
import io.springfox.springfoxswagger2.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/users")
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/v1/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/v1/users")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @DeleteMapping("/v1/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
