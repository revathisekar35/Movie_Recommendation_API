package com.techreturners.moviemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.moviemanager.model.Review;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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

    private final String DATE_FORMAT = "yyyy-MM-dd";

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(reviewManagerController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetAllReviewsReturnsReviews() throws Exception {

        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, "This is the comment for Movie 1", (new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), 1L, 1L));
        reviews.add(new Review(2L, "This is the comment for Movie 2", (new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), 1L, 1L));

        when(mockReviewManagerServiceImpl.getAllReviews()).thenReturn(reviews);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/review/getAllReviews"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("This is the comment for Movie 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].comment").value("This is the comment for Movie 2"));
    }

    @Test
    public void testGetMappingGetReviewById() throws Exception {

        Review review = new Review(4L, "This is the comment for Movie 4", (new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), 1L, 1L);

        when(mockReviewManagerServiceImpl.getReviewById(review.getId())).thenReturn(review);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/review/" + review.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("This is the comment for Movie 4"));
    }

    @Test
    public void testPostMappingAddAReview() throws Exception {

        Review review = new Review(4L, "This is the comment for Movie 4", (new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), 1L, 1L);

        when(mockReviewManagerServiceImpl.insertReview(review)).thenReturn(review);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/review/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(review)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(mockReviewManagerServiceImpl, times(1)).insertReview(review);
    }

    @Test
    public void testPutMappingUpdateAReview() throws Exception {

        Review review = new Review(4L, "This is the updated comment for Movie 4", (new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), 1L, 1L);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/review/update/" + review.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(review)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockReviewManagerServiceImpl, times(1)).updateReviewById(review.getId(), review);
    }

    @Test
    public void testDeleteMappingDeleteAReview() throws Exception {

        Review review = new Review(4L, "This is the updated comment for Movie 4", (new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), 1L, 1L);
        when(mockReviewManagerServiceImpl.getReviewById(review.getId())).thenReturn(review);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/review/delete/" + review.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(review)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockReviewManagerServiceImpl, times(1)).deleteReviewById(review.getId());
    }
}
