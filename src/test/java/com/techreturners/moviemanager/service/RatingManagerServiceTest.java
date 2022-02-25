package com.techreturners.moviemanager.service;

import com.techreturners.moviemanager.model.Rating;
import com.techreturners.moviemanager.repository.RatingManagerRepository;
import com.techreturners.moviemanager.service.impl.RatingManagerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class RatingManagerServiceTest {

    @Mock
    private RatingManagerRepository mockRatingManagerRepository;

    @InjectMocks
    private RatingManagerServiceImpl ratingManagerService;

    @Test
    public void testGetAllRatingsReturnsListOfRatings() throws Exception {

        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(1L, 1L, 1L,1L,1L,1.0));
        ratings.add(new Rating(2L, 2L, 2L,2L,2L,2.0));
        ratings.add(new Rating(3L, 3L, 3L,3L,3L,3.0));

        when(mockRatingManagerRepository.findAll()).thenReturn(ratings);

        List<Rating> actualResult;
        actualResult = ratingManagerService.getAllRatings();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(ratings);
    }

    @Test
    public void testAddARating() throws Exception {

        var rating = new Rating(4L, 4L, 4L,4L,4L,4.0);

        when(mockRatingManagerRepository.save(rating)).thenReturn(rating);

        Rating actualResult = ratingManagerService.insertRating(rating);

        assertThat(actualResult).isEqualTo(rating);
    }

    @Test
    public void testGetRatingById() throws Exception {

        Long ratingId = 5L;
        var Rating = new Rating(5L, 5L, 5L,5L,5L,5.0);

        when(mockRatingManagerRepository.findById(ratingId)).thenReturn(Optional.of(Rating));

        Rating actualResult = ratingManagerService.getRatingById(ratingId);

        assertThat(actualResult).isEqualTo(Rating);
    }

    @Test
    public void testUpdateRatingById() throws Exception {

        Long RatingId = 5L;
        var rating = new Rating(5L, 5L, 5L,5L,5L,5.0);

        when(mockRatingManagerRepository.findById(RatingId)).thenReturn(Optional.of(rating));
        when(mockRatingManagerRepository.save(rating)).thenReturn(rating);

        ratingManagerService.updateRatingById(RatingId, rating);

        verify(mockRatingManagerRepository, times(1)).save(rating);
    }
    @Test
    public void testDeleteRatingById() throws Exception {
        //add
        Long ratingId = 5L;
        var rating = new Rating(5L, 5L, 5L,5L,5L,5.0);

        when(mockRatingManagerRepository.save(rating)).thenReturn(rating);

        //delete
        ratingManagerService.deleteRatingById(ratingId);
        verify(mockRatingManagerRepository, times(1)).deleteById(rating.getId());
    }
}
