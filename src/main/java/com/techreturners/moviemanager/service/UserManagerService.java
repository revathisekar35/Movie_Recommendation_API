package com.techreturners.moviemanager.service;

import com.techreturners.moviemanager.model.User;

import java.util.List;

public interface UserManagerService {
    List<User> getAllUsers();

    User insertUser(User user);

    User getUserById(Long id);

    void updateUserById(Long id, User user);

    void deleteUserById(Long id);
}