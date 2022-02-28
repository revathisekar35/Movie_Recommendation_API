package com.techreturners.moviemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.moviemanager.model.Movie;
import com.techreturners.moviemanager.model.Review;
import com.techreturners.moviemanager.model.User;
import com.techreturners.moviemanager.service.impl.ReviewManagerServiceImpl;

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
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ReviewManagerControllerTest {
    @Mock
    private ReviewManagerServiceImpl mockReviewManagerServiceImpl;

    @InjectMocks
    private ReviewManagerController reviewManagerController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(reviewManagerController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetAllReviewsReturnsReviews() throws Exception {

        Date myDate = new Date("10/02/2021");
        Date myDate1 = new Date("06/05/2022");
        Movie m = new Movie();
        User u = new User();
        Movie m1 = new Movie();
        User u1 = new User();
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, "This is the comment for Movie 1", myDate, m, u));
        reviews.add(new Review(2L, "This is the comment for Movie 2", myDate1, m1, u1));

        when(mockReviewManagerServiceImpl.getAllReviews()).thenReturn(reviews);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/review/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("This is the comment for Movie 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].comment").value("This is the comment for Movie 2"));
    }

    @Test
    public void testGetMappingGetReviewById() throws Exception {
        Date myDate = new Date("10/02/2021");
        Movie m = new Movie();
        User u = new User();
        Review review = new Review(4L, "This is the comment for Movie 4", myDate, m, u);

        when(mockReviewManagerServiceImpl.getReviewById(review.getId())).thenReturn(review);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/review/" + review.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("This is the comment for Movie 4"));
    }

    @Test
    public void testPostMappingAddAReview() throws Exception {

        Date myDate = new Date("10/02/2021");
        Movie m = new Movie();
        User u = new User();
        Review review = new Review(4L, "This is the comment for Movie 4", myDate, m, u);

        when(mockReviewManagerServiceImpl.insertReview(review)).thenReturn(review);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/review/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(review)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(mockReviewManagerServiceImpl, times(1)).insertReview(review);
    }

    @Test
    public void testPutMappingUpdateAReview() throws Exception {

        Date myDate = new Date("10/02/2021");
        Movie m = new Movie();
        User u = new User();
        Review review = new Review(4L, "This is the updated comment for Movie 4", myDate, m, u);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/review/" + review.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(review)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockReviewManagerServiceImpl, times(1)).updateReviewById(review.getId(), review);
    }

    @Test
    public void testDeleteMappingDeleteAReview() throws Exception {

        Date myDate = new Date("10/02/2021");
        Movie m = new Movie();
        User u = new User();
        Review review = new Review(4L, "This is the updated comment for Movie 4", myDate, m, u);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/review/" + review.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(review)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockReviewManagerServiceImpl, times(1)).deleteReviewById(review.getId());
    }
}
