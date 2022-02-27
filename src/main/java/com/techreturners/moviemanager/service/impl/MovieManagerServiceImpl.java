package com.techreturners.moviemanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techreturners.moviemanager.model.Movie;
import com.techreturners.moviemanager.repository.MovieManagerRepository;
import com.techreturners.moviemanager.service.MovieManagerService;

@Service
public class MovieManagerServiceImpl implements MovieManagerService {

	@Autowired
	MovieManagerRepository movieManagerRepository;

	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<>();
		movieManagerRepository.findAll().forEach(movies::add);
		return movies;
	}

	@Override
	public Movie insertMovie(Movie movie) {
		return movieManagerRepository.save(movie);
	}

	@Override
	public Movie getMovieById(Long id) {
		return movieManagerRepository.findById(id).get();
	}

	@Override
	public void updateMovieById(Long id, Movie movie) {
		Movie retrievedMovie = movieManagerRepository.findById(id).get();
		retrievedMovie.setName(movie.getName());
		retrievedMovie.setDescription(movie.getDescription());
		retrievedMovie.setReleasYear(movie.getReleasYear());
		retrievedMovie.setPerson(movie.getPerson());
		movieManagerRepository.save(movie);
	}

	@Override
	public void deleteMovieById(Long id) {
		movieManagerRepository.deleteById(id);
	}
	
	public List<Movie> getMoviesByActor(String actorName){
		return movieManagerRepository.getMoviesByActor(actorName);		
	}
	
	public List<Movie> getMoviesByDirector(String directorName){
		return movieManagerRepository.getMoviesByDirector(directorName);		
	}
}
