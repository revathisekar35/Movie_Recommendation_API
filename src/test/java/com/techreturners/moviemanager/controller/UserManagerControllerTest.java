package com.techreturners.moviemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.moviemanager.model.User;
import com.techreturners.moviemanager.service.impl.UserManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserManagerControllerTest {
    @Mock
    private UserManagerServiceImpl mockUserManagerServiceImpl;

    @InjectMocks
    private UserManagerController userManagerController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(userManagerController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetAllUsersReturnsUsers() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(new User(1L, "admin", "admin@gmail.com", 30, true));
        users.add(new User(2L, "guest", "guest@gmail.com", 20, false));

        when(mockUserManagerServiceImpl.getAllUsers()).thenReturn(users);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/user/getAllUsers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("admin@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value("30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].admin").value(true))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("guest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("guest@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value("20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].admin").value(false));
    }

    @Test
    public void testGetMappingGetUserById() throws Exception {
        User user = new User(1L, "admin", "admin@gmail.com", 30, true);
        when(mockUserManagerServiceImpl.getUserById(user.getId())).thenReturn(user);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/user/" + user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("admin@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(true));
    }

    @Test
    public void testPostMappingAddUserAdmin() throws Exception {
        User user = new User(1L, "admin", "admin@gmail.com", 30, true);
        when(mockUserManagerServiceImpl.insertUser(user)).thenReturn(user);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/user/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(mockUserManagerServiceImpl, times(1)).insertUser(user);
    }

    @Test
    public void testPostMappingAddUserGuest() throws Exception {
        User user = new User(2L, "guest", "admin@gmail.com", 20, false);
        when(mockUserManagerServiceImpl.insertUser(user)).thenReturn(user);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/user/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(mockUserManagerServiceImpl, times(1)).insertUser(user);
    }

    @Test
    public void testPutMappingUpdateUser() throws Exception {
        User user = new User(2L, "guest", "guest@gmail.com", 20, false);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/user/update/" + user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockUserManagerServiceImpl, times(1)).updateUserById(user.getId(), user);
    }

    @Test
    public void testDeleteMappingDeleteUser() throws Exception {
        User user = new User(2L, "guest", "guest@gmail.com", 20, false);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/user/delete/" + user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockUserManagerServiceImpl, times(1)).deleteUserById(user.getId());
    }
}
