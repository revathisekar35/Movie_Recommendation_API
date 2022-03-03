package com.techreturners.moviemanager.service;

import com.techreturners.moviemanager.model.Review;
import com.techreturners.moviemanager.repository.ReviewManagerRepository;
import com.techreturners.moviemanager.service.impl.ReviewManagerServiceImpl;
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

@DataJpaTest
public class ReviewManagerServiceTest {

    @Mock
    private ReviewManagerRepository mockReviewManagerRepository;

    @InjectMocks
    private ReviewManagerServiceImpl reviewManagerServiceImpl;

    @Test
    public void testGetAllReviewsReturnsListOfReviews() {

        Date myDate = new Date("2021-02-10");
        Date myDate1 = new Date("2021-02-10");
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, "This is the comment for Movie 1", myDate, 1L, 1L));
        reviews.add(new Review(2L, "This is the comment for Movie 2", myDate1, 1L, 1L));

        when(mockReviewManagerRepository.findAll()).thenReturn(reviews);

        List<Review> actualResult = reviewManagerServiceImpl.getAllReviews();

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(reviews);
    }

    @Test
    public void testAddAReview(){

        Date myDate = new Date("2021-02-10");

        var review = new Review(3L, "This is the comment for Movie 3", myDate, 1L, 1L);

        when(mockReviewManagerRepository.save(review)).thenReturn(review);

        Review actualResult = reviewManagerServiceImpl.insertReview(review);

        assertThat(actualResult).isEqualTo(review);
    }

    @Test
    public void testGetReviewById(){

        Date myDate = new Date("10/02/2021");
        Long reviewId = 5L;
        var review = new Review(5L, "This is the comment for Movie 5", myDate, 1L, 1L);

        when(mockReviewManagerRepository.findById(reviewId)).thenReturn(Optional.of(review));

        Review actualResult = reviewManagerServiceImpl.getReviewById(reviewId);

        assertThat(actualResult).isEqualTo(review);
    }

    @Test
    public void testUpdateReviewById(){

        Date myDate = new Date("2021-02-10");

        Long reviewId = 5L;
        var review = new Review(5L, "This is the updated comment for Movie 5", myDate, 1L, 1L);

        when(mockReviewManagerRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(mockReviewManagerRepository.save(review)).thenReturn(review);

        reviewManagerServiceImpl.updateReviewById(reviewId, review);

        verify(mockReviewManagerRepository, times(1)).save(review);
    }

    @Test
    public void testDeleteReviewById(){

        Date myDate = new Date("2021-02-10");

        Long reviewId = 5L;
        var review = new Review(5L, "This is the updated comment for Movie 5", myDate, 1L, 1L);

        when(mockReviewManagerRepository.findById(reviewId)).thenReturn(Optional.of(review));
        reviewManagerServiceImpl.deleteReviewById(reviewId);

        verify(mockReviewManagerRepository).deleteById(reviewId);
    }
}
