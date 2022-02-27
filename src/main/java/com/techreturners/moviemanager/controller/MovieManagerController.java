package com.techreturners.moviemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techreturners.moviemanager.service.MovieManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.moviemanager.model.Movie;

@RestController
@RequestMapping("/api/movie")
public class MovieManagerController {

	@Autowired
	private MovieManagerService movieManagerService;

	@GetMapping("/getAllMovies")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = movieManagerService.getAllMovies();
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping({ "/{movieId}" })
	public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
		return new ResponseEntity<>(movieManagerService.getMovieById(movieId), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		Movie newMovie = movieManagerService.insertMovie(movie);
		return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
	}

	@PutMapping({ "/update/{movieId}" })
	public ResponseEntity<Movie> updateMovieById(@PathVariable("movieId") Long movieId, @RequestBody Movie movie) {
		movieManagerService.updateMovieById(movieId, movie);
		return new ResponseEntity<>(movieManagerService.getMovieById(movieId), HttpStatus.OK);
	}

	@DeleteMapping({ "/delete/{movieId}" })
	public ResponseEntity<Movie> deleteMovieById(@PathVariable Long movieId) {
		movieManagerService.deleteMovieById(movieId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping({ "/actorName/{actorName}" })
	public ResponseEntity<List<Movie>> getMovieByActor(@PathVariable String actorName) {
		List<Movie> movies = movieManagerService.getMoviesByActor(actorName);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping({ "/directorName/{directorName}" })
	public ResponseEntity<List<Movie>> getMovieByDirector(@PathVariable String directorName) {
		List<Movie> movies = movieManagerService.getMoviesByDirector(directorName);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
}
