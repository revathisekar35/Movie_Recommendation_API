package com.techreturners.moviemanager.service.impl;

import com.techreturners.moviemanager.model.User;
import com.techreturners.moviemanager.repository.UserManagerRepository;
import com.techreturners.moviemanager.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    UserManagerRepository userManagerRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userManagerRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User insertUser(User user) {
        return userManagerRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userManagerRepository.findById(id).get();
    }

    @Override
    public void updateUserById(Long id, User user) {
        User retrievedUser = userManagerRepository.findById(id).get();
        retrievedUser.setName(user.getName());
        retrievedUser.setEmail(user.getEmail());
        userManagerRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userManagerRepository.deleteById(id);
    }
}
