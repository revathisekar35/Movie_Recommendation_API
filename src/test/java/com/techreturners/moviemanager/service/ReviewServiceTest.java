package com.techreturners.moviemanager.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.techreturners.moviemanager.model.Movie;
import com.techreturners.moviemanager.model.Review;
import com.techreturners.moviemanager.model.User;
import com.techreturners.moviemanager.repository.MovieManagerRepository;
import com.techreturners.moviemanager.repository.ReviewRepository;
import com.techreturners.moviemanager.service.impl.MovieManagerServiceImpl;
import com.techreturners.moviemanager.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class ReviewServiceTest {

    @Mock
    private ReviewRepository mockReviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewServiceImpl;

    @Test
    public void testGetAllReviewsReturnsListOfReviews() {

        Date myDate = new Date("10/02/2021");
        Date myDate1 = new Date("06/05/2022");
        Movie m = new Movie();
        User u = new User();
        Movie m1 = new Movie();
        User u1 = new User();
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, "This is the comment for Movie 1", myDate, m, u));
        reviews.add(new Review(2L, "This is the comment for Movie 2", myDate1, m1, u1));

        when(mockReviewRepository.findAll()).thenReturn(reviews);

        List<Review> actualResult = reviewServiceImpl.getAllReviews();

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(reviews);
    }

    @Test
    public void testAddAReview() {

        Date myDate = new Date("10/02/2021");
        Movie m = new Movie();
        User u = new User();
        var review = new Review(3L, "This is the comment for Movie 3", myDate, m, u);

        when(mockReviewRepository.save(review)).thenReturn(review);

        Review actualResult = reviewServiceImpl.insertReview(review);

        assertThat(actualResult).isEqualTo(review);
    }

    @Test
    public void testGetReviewById() {

        Date myDate = new Date("10/02/2021");
        Movie m = new Movie();
        User u = new User();
        Long reviewId = 5L;
        var review = new Review(5L, "This is the comment for Movie 5", myDate, m, u);

        when(mockReviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        Review actualResult = reviewServiceImpl.getReviewById(reviewId);

        assertThat(actualResult).isEqualTo(review);
    }

    @Test
    public void testUpdateReviewById() {

        Date myDate = new Date("10/02/2021");
        Movie m = new Movie();
        User u = new User();
        Long reviewId = 5L;
        var review = new Review(5L, "This is the updated comment for Movie 5", myDate, m, u);

        when(mockReviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(mockReviewRepository.save(review)).thenReturn(review);

        reviewServiceImpl.updateReviewById(reviewId, review);

        verify(mockReviewRepository, times(1)).save(review);
    }

    @Test
    public void testDeleteReviewById() {

        Date myDate = new Date("10/02/2021");
        Movie m = new Movie();
        User u = new User();
        Long reviewId = 5L;
        var review = new Review(5L, "This is the updated comment for Movie 5", myDate, m, u);

        when(mockReviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        reviewServiceImpl.deleteReviewById(reviewId);

        verify(mockReviewRepository).deleteById(reviewId);
    }
}

