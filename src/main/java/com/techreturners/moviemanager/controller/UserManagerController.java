package com.techreturners.moviemanager.controller;

import java.util.List;

import com.techreturners.moviemanager.model.User;
import com.techreturners.moviemanager.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserManagerController {

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userManagerService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping({ "/{userId}" })
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userManagerService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userManagerService.insertUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping({"/update/{userId}"})
    public ResponseEntity<User> updateUserById(@PathVariable("userId") Long userId, @RequestBody User user) {
        userManagerService.updateUserById(userId, user);
        return new ResponseEntity<>(userManagerService.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping({"/delete/{userId}"})
    public ResponseEntity<User> deleteUserById(@PathVariable Long userId) {
        userManagerService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
