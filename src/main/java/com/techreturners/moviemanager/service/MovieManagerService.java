package com.techreturners.moviemanager.service;

import java.util.List;

import com.techreturners.moviemanager.model.*;

public interface MovieManagerService {

	List<Movie> getAllMovies();

	Movie insertMovie(Movie movie);

	Movie getMovieById(Long id);

	void updateMovieById(Long id, Movie movie);

	void deleteMovieById(Long id);
	
	List<Movie> getMoviesByActor(String actor);
	
	List<Movie> getMoviesByDirector(String directorName);
	
	List<Movie> getMoviesByReleasedYear(int year);
	
	List<Person> getMovieCrewList();

	List<Movie> getMoviesByGenre(Genre genre);

	List<Movie> getMoviesByCertification(Certification certification);

	List<Movie> getMoviesByLanguage(Language language);

	List<Movie> getMoviesByCountry(Country country);
}
