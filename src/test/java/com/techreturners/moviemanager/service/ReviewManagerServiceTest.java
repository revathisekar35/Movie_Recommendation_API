package com.techreturners.moviemanager.service;

import com.techreturners.moviemanager.model.Movie;
import com.techreturners.moviemanager.model.Review;
import com.techreturners.moviemanager.model.User;
import com.techreturners.moviemanager.repository.ReviewManagerRepository;
import com.techreturners.moviemanager.service.impl.ReviewManagerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    private final String DATE_FORMAT ="yyyy-MM-dd";

    @Test
    public void testGetAllReviewsReturnsListOfReviews() throws ParseException {

        Date myDate = new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString());
        Date myDate1 = new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString());
        Movie m = new Movie();
        User u = new User();
        Movie m1 = new Movie();
        User u1 = new User();
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, "This is the comment for Movie 1", myDate, m, u));
        reviews.add(new Review(2L, "This is the comment for Movie 2", myDate1, m1, u1));

        when(mockReviewManagerRepository.findAll()).thenReturn(reviews);

        List<Review> actualResult = reviewManagerServiceImpl.getAllReviews();

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(reviews);
    }

    @Test
    public void testAddAReview() throws ParseException{

        Date myDate = new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString());
        Movie m = new Movie();
        User u = new User();
        var review = new Review(3L, "This is the comment for Movie 3", myDate, m, u);

        when(mockReviewManagerRepository.save(review)).thenReturn(review);

        Review actualResult = reviewManagerServiceImpl.insertReview(review);

        assertThat(actualResult).isEqualTo(review);
    }

    @Test
    public void testGetReviewById() throws ParseException{

        Date myDate = new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString());
        Movie m = new Movie();
        User u = new User();
        Long reviewId = 5L;
        var review = new Review(5L, "This is the comment for Movie 5", myDate, m, u);

        when(mockReviewManagerRepository.findById(reviewId)).thenReturn(Optional.of(review));

        Review actualResult = reviewManagerServiceImpl.getReviewById(reviewId);

        assertThat(actualResult).isEqualTo(review);
    }

    @Test
    public void testUpdateReviewById() throws ParseException{

        Date myDate = new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString());
        Movie m = new Movie();
        User u = new User();
        Long reviewId = 5L;
        var review = new Review(5L, "This is the updated comment for Movie 5", myDate, m, u);

        when(mockReviewManagerRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(mockReviewManagerRepository.save(review)).thenReturn(review);

        reviewManagerServiceImpl.updateReviewById(reviewId, review);

        verify(mockReviewManagerRepository, times(1)).save(review);
    }

    @Test
    public void testDeleteReviewById() throws ParseException{

        Date myDate = new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString());
        Movie m = new Movie();
        User u = new User();
        Long reviewId = 5L;
        var review = new Review(5L, "This is the updated comment for Movie 5", myDate, m, u);

        when(mockReviewManagerRepository.findById(reviewId)).thenReturn(Optional.of(review));
        reviewManagerServiceImpl.deleteReviewById(reviewId);

        verify(mockReviewManagerRepository).deleteById(reviewId);
    }
}