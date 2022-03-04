package com.techreturners.moviemanager.service;

import com.techreturners.moviemanager.model.Review;
import com.techreturners.moviemanager.model.User;
import com.techreturners.moviemanager.repository.UserManagerRepository;
import com.techreturners.moviemanager.service.impl.UserManagerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
public class UserManagerServiceTest {

    @Mock
    private UserManagerRepository mockUserManagerRepository;

    @InjectMocks
    private UserManagerServiceImpl userManagerServiceImpl;

    @Test
    public void testGetAllUsersReturnsListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "admin", "admin@gmail.com", 30, true));
        users.add(new User(2L, "guest", "guest@gmail.com", 20, false));

        when(mockUserManagerRepository.findAll()).thenReturn(users);

        List<User> actualResult = userManagerServiceImpl.getAllUsers();

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(users);
    }

    @Test
    public void testAddUser(){
        var user = new User(1L, "admin", "admin@gmail.com", 30, true);

        when(mockUserManagerRepository.save(user)).thenReturn(user);

        User actualResult = userManagerServiceImpl.insertUser(user);

        assertThat(actualResult).isEqualTo(user);
    }
    @Test
    public void testGetUserById(){
        Long userId = 3L;
        var user = new User(3L, "user", "user@gmail.com", 10, false);

        when(mockUserManagerRepository.findById(userId)).thenReturn(Optional.of(user));

        User actualResult = userManagerServiceImpl.getUserById(userId);

        assertThat(actualResult).isEqualTo(user);
    }

    @Test
    public void testUpdateUserById(){

        Long userId = 3L;
        var user = new User(3L, "user", "user@gmail.com", 10, false);

        when(mockUserManagerRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mockUserManagerRepository.save(user)).thenReturn(user);

        userManagerServiceImpl.updateUserById(userId, user);

        verify(mockUserManagerRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteReviewById(){

        Long userId = 3L;
        var user = new User(3L, "user", "user@gmail.com", 10, false);

        when(mockUserManagerRepository.findById(userId)).thenReturn(Optional.of(user));
        userManagerServiceImpl.deleteUserById(userId);

        verify(mockUserManagerRepository).deleteById(userId);
    }
}
