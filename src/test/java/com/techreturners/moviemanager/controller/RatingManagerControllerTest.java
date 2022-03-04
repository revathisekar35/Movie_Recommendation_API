package com.techreturners.moviemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.moviemanager.model.*;
import com.techreturners.moviemanager.service.impl.RatingManagerServiceImpl;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.type.descriptor.java.JdbcDateTypeDescriptor.DATE_FORMAT;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class RatingManagerControllerTest {

    @Mock
    private RatingManagerServiceImpl mockRatingManagerServiceImpl;

    @InjectMocks
    private RatingManagerController RatingManagerController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(RatingManagerController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetAllRatingsReturnsRatings() throws Exception {
        Movie m1 = new Movie();
        Movie m2 = new Movie();
//        User u1 = new User();
//        User u2 = new User();
        List<Rating> ratings = new ArrayList<>();
        /*ratings.add(new Rating(1L, u1, m1, 1L, 1L, 1.0));
        ratings.add(new Rating(2L, u2, m2, 2L, 2L, 2.0));*/
        ratings.add(new Rating(1L, m1, 1L, 1L, 1.0));
        ratings.add(new Rating(2L, m2, 2L, 2L, 2.0));

        when(mockRatingManagerServiceImpl.getAllRatings()).thenReturn(ratings);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/rating/getAllRatings"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].likes").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dislikes").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalRating").value(1.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].likes").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dislikes").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalRating").value(2.0));
    }

    @Test
    public void testGetMappingGetRatingById() throws Exception {
        Movie m3 = new Movie();
        //User u3 = new User();
//        Rating rating = new Rating(3L, u3, m3, 3L, 3L, 3.0);
        Rating rating = new Rating(3L, m3, 3L, 3L, 3.0);
        when(mockRatingManagerServiceImpl.getRatingById(rating.getId())).thenReturn(rating);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/rating/" + rating.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.likes").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dislikes").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRating").value(3.0));
    }

    @Test
    public void testPostMappingAddARating() throws Exception {
        Movie m3 = new Movie();
        //User u3 = new User();
        //Rating rating = new Rating(3L, u3, m3, 3L, 3L, 3.0);
        Rating rating = new Rating(3L, m3, 3L, 3L, 3.0);

        when(mockRatingManagerServiceImpl.insertRating(rating)).thenReturn(rating);

        this.mockMvcController
                .perform(MockMvcRequestBuilders.post("/api/v1/rating/create/").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rating)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(mockRatingManagerServiceImpl, times(1)).insertRating(rating);
    }

    @Test
    public void testPutMappingUpdateARating() throws Exception {
        Movie m3 = new Movie();
        //User u3 = new User();
        //Rating rating = new Rating(3L, u3, m3, 3L, 3L, 3.0);
        Rating rating = new Rating(3L, m3, 3L, 3L, 3.0);

        this.mockMvcController
                .perform(MockMvcRequestBuilders.put("/api/v1/rating/update/" + rating.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(rating)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockRatingManagerServiceImpl, times(1)).updateRatingById(rating.getId(), rating);
    }

    @Test
    public void testDeleteMappingForDeleteAMovie() throws Exception {
        Movie m3 = new Movie();
        //User u3 = new User();
        //Rating rating = new Rating(3L, u3, m3, 3L, 3L, 3.0);
        Rating rating = new Rating(3L, m3, 3L, 3L, 3.0);

        when(mockRatingManagerServiceImpl.insertRating(rating)).thenReturn(rating);
        this.mockMvcController.perform(MockMvcRequestBuilders.delete("/api/v1/rating/delete/" + rating.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(mockRatingManagerServiceImpl, times(1)).deleteRatingById(rating.getId());
    }
}
