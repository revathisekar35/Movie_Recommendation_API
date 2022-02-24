package com.techreturners.moviemanager.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.techreturners.moviemanager.repository.MovieManagerRepository;
import com.techreturners.moviemanager.service.impl.MovieManagerServiceImpl;
import com.techreturners.moviemanager.model.Movie;

@DataJpaTest
public class MovieManagerServiceTest {

    @Mock
    private MovieManagerRepository mockMovieManagerRepository;
    
    @InjectMocks
    private MovieManagerServiceImpl movieManagerService;

    @Test
    public void testGetAllMoviesReturnsListOfMovies() {

        List<Movie> Movies = new ArrayList<>();
        Movies.add(new Movie(1L, "Movie One", "This is the description for Movie One",null));
        Movies.add(new Movie(2L, "Movie Two", "This is the description for Movie Two",null));
        Movies.add(new Movie(3L, "Movie Three", "This is the description for Movie Three",null));

        when(mockMovieManagerRepository.findAll()).thenReturn(Movies);

        List<Movie> actualResult;
			actualResult = movieManagerService.getAllMovies();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(Movies);
    }

    @Test
    public void testAddAMovie() {

        var Movie = new Movie(4L, "Movie Four", "This is the description for Movie Four", null);

        when(mockMovieManagerRepository.save(Movie)).thenReturn(Movie);

        Movie actualResult = movieManagerService.insertMovie(Movie);

        assertThat(actualResult).isEqualTo(Movie);
    }

    @Test
    public void testGetMovieById() {

        Long MovieId = 5L;
        var Movie = new Movie(5L, "Movie Five", "This is the description for Movie Five",null);

        when(mockMovieManagerRepository.findById(MovieId)).thenReturn(Optional.of(Movie));

        Movie actualResult = movieManagerService.getMovieById(MovieId);

        assertThat(actualResult).isEqualTo(Movie);
    }

    //User Story 4 - Update Movie By Id Solution
    @Test
    public void testUpdateMovieById() {

        Long MovieId = 5L;
        var Movie = new Movie(5L, "Movie Five", "This is the description for Movie Five", null);

        when(mockMovieManagerRepository.findById(MovieId)).thenReturn(Optional.of(Movie));
        when(mockMovieManagerRepository.save(Movie)).thenReturn(Movie);

        movieManagerService.updateMovieById(MovieId, Movie);

        verify(mockMovieManagerRepository, times(1)).save(Movie);
    }

}
