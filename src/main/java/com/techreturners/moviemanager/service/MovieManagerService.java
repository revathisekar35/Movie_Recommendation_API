package com.techreturners.moviemanager.service;

import java.util.List;

import com.techreturners.moviemanager.model.Movie;
import com.techreturners.moviemanager.model.Person;

public interface MovieManagerService {

	List<Movie> getAllMovies();

	Movie insertMovie(Movie movie);

	Movie getMovieById(Long id);

	Movie updateMovieById(Long id, Movie movie);

	void deleteMovieById(Long id);
	
	List<Movie> getMoviesByActor(String actor);
	
	List<Movie> getMoviesByDirector(String directorName);
	
	List<Movie> getMoviesByReleasedYear(int year);
	
	List<Person> getMovieCrewList();

	List<Movie> getMoviesByGenre(String genre);

	List<Movie> getMoviesByCertification(String certification);

	List<Movie> getMoviesByLanguage(String language);

	List<Movie> getMoviesByCountry(String country);
	
	List<Movie> getMoviesByRating(Double rating);
}
