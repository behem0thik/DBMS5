package ru.rgr.Blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rgr.Blog.model.User;
import ru.rgr.Blog.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        List<User> allUsers = userService.findAll();
        log.info("Got all Users");
        return allUsers;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") UUID id) {
        User foundUser = userService.findUserById(id);
        log.info("Got a User with given id: {}", id);
        return foundUser;
    }

    @GetMapping("/{username}")
    public User findUserByUsername(@PathVariable("username") String username) {
        User foundUser = userService.findUserByUsername(username);
        log.info("Got a User with given username: {}", username);
        return foundUser;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        User newUser = userService.create(user);
        log.info("Uploaded a new User");
        return newUser;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") UUID id, @Valid @ModelAttribute User user) {
        User updatedUser = userService.update(id, user);
        log.info("Updated a User with given id: {}", id);
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        log.info("Deleted a User with given id={}", id);
    }
}
