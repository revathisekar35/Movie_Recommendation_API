package com.techreturners.moviemanager.service;

import com.techreturners.moviemanager.exception.MovieNotFoundException;
import com.techreturners.moviemanager.model.*;
import com.techreturners.moviemanager.repository.RatingManagerRepository;
import com.techreturners.moviemanager.service.impl.MovieManagerServiceImpl;
import com.techreturners.moviemanager.service.impl.RatingManagerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private MovieManagerServiceImpl movieManagerService;

    @Test
    public void testGetAllRatingsReturnsListOfRatings()  {
        Movie m1 = new Movie();
        Movie m2 = new Movie();
        Movie m3 = new Movie();
//        User u1 = new User();
//        User u2 = new User();
//        User u3 = new User();
        List<Rating> ratings = new ArrayList<>();
        /*ratings.add(new Rating(1L, u1, m1,1L,1L,1.0));
        ratings.add(new Rating(2L, u2, m2,2L,2L,2.0));
        ratings.add(new Rating(3L, u3, m3,3L,3L,3.0));*/
        ratings.add(new Rating(1L, m1,1L,1L,1.0));
        ratings.add(new Rating(2L, m2,2L,2L,2.0));
        ratings.add(new Rating(3L, m3,3L,3L,3.0));

        when(mockRatingManagerRepository.findAll()).thenReturn(ratings);

        List<Rating> actualResult;
        actualResult = ratingManagerService.getAllRatings();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(ratings);
    }

    @Test
    public void testAddARating() throws MovieNotFoundException, ParseException {
        Person person  = new Person(1L, "Tom", PersonRole.Actor);
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        String DATE_FORMAT = "yyyy-MM-dd";
        var m1 = new Movie(4L, "Movie Four", "This is the description for Movie Four",
               (new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList);

        //User u1 = new User();
        //var rating = new Rating(4L, u1, m1,4L,4L,4.0);
        var rating = new Rating(4L, m1,4L,4L,4.0);

        when(mockRatingManagerRepository.save(rating)).thenReturn(rating);

        Rating actualResult = ratingManagerService.insertRating(rating);

        assertThat(actualResult).isEqualTo(rating);
    }

    @Test
    public void testGetRatingById(){
        Movie m1 = new Movie();
        //User u1 = new User();
        Long ratingId = 5L;
        //var Rating = new Rating(5L, u1, m1,5L,5L,5.0);
        var Rating = new Rating(5L, m1,5L,5L,5.0);

        when(mockRatingManagerRepository.findById(ratingId)).thenReturn(Optional.of(Rating));

        Rating actualResult = ratingManagerService.getRatingById(ratingId);

        assertThat(actualResult).isEqualTo(Rating);
    }

    @Test
    public void testUpdateRatingById() {
        Movie m1 = new Movie();
        //User u1 = new User();
        Long ratingId = 5L;
        //var rating = new Rating(5L, u1, m1,5L,5L,5.0);
        var rating = new Rating(5L, m1,5L,5L,5.0);

        when(mockRatingManagerRepository.findById(ratingId)).thenReturn(Optional.of(rating));
        when(mockRatingManagerRepository.save(rating)).thenReturn(rating);

        ratingManagerService.updateRatingById(ratingId, rating);

        verify(mockRatingManagerRepository, times(1)).save(rating);
    }
    @Test
    public void testDeleteRatingById() {
        //add
        Movie m1 = new Movie();
        //User u1 = new User();
        Long ratingId = 5L;
        //var rating = new Rating(5L, u1, m1,5L,5L,5.0);
        var rating = new Rating(5L, m1,5L,5L,5.0);

        when(mockRatingManagerRepository.save(rating)).thenReturn(rating);

        //delete
        ratingManagerService.deleteRatingById(ratingId);
        verify(mockRatingManagerRepository, times(1)).deleteById(rating.getId());
    }
}
