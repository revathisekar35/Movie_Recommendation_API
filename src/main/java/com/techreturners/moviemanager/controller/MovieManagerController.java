package com.techreturners.moviemanager.controller;

import java.util.List;

import com.techreturners.moviemanager.model.*;
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

import com.techreturners.moviemanager.service.MovieManagerService;

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

	@GetMapping("/year/{releasedYear}")
	public ResponseEntity<List<Movie>> getMoviesByReleaseYear(@PathVariable int releasedYear) {
		List<Movie> movies = movieManagerService.getMoviesByReleasedYear(releasedYear);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping("/movieCrewList")
	public ResponseEntity<List<Person>> getMovieCrewList() {
		List<Person> person = movieManagerService.getMovieCrewList();
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable Genre genre) {
		List<Movie> movies = movieManagerService.getMoviesByGenre(genre);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping("/certification/{certification}")
	public ResponseEntity<List<Movie>> getMoviesByCertification(@PathVariable Certification certification) {
		List<Movie> movies = movieManagerService.getMoviesByCertification(certification);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping("/language/{language}")
	public ResponseEntity<List<Movie>> getMoviesByLanguage(@PathVariable Language language) {
		List<Movie> movies = movieManagerService.getMoviesByLanguage(language);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping("/country/{country}")
	public ResponseEntity<List<Movie>> getMoviesByLanguage(@PathVariable Country country) {
		List<Movie> movies = movieManagerService.getMoviesByCountry(country);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
}
