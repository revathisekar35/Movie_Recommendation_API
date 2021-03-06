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

import com.techreturners.moviemanager.service.MovieManagerService;
import com.techreturners.moviemanager.config.AppConfig;
import com.techreturners.moviemanager.config.TwilioInitilizer;
import com.techreturners.moviemanager.model.Movie;
import com.techreturners.moviemanager.model.Person;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieManagerController {

	@Autowired
	private MovieManagerService movieManagerService;
	
	@Autowired
	AppConfig appconfig;

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
		//new TwilioInitilizer(appconfig.getSid(), appconfig.getAuthId(), appconfig.getToNumber(), appconfig.getFromNumber(), newMovie.getName()+" newly added into database");
		return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
	}

	@PutMapping({ "/update/{movieId}" })
	public ResponseEntity<Movie> updateMovieById(@PathVariable("movieId") Long movieId, @RequestBody Movie movie) {
		Movie updatedMovie = movieManagerService.updateMovieById(movieId, movie);
		return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
	}

	@DeleteMapping({ "/delete/{movieId}" })
	public ResponseEntity deleteMovieById(@PathVariable Long movieId) {
		movieManagerService.deleteMovieById(movieId);
		return new ResponseEntity<>("Movie deleted successfully",HttpStatus.OK);
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
	public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
		List<Movie> movies = movieManagerService.getMoviesByGenre(genre);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping("/certification/{certification}")
	public ResponseEntity<List<Movie>> getMoviesByCertification(@PathVariable String certification) {
		List<Movie> movies = movieManagerService.getMoviesByCertification(certification);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping("/language/{language}")
	public ResponseEntity<List<Movie>> getMoviesByLanguage(@PathVariable String language) {
		List<Movie> movies = movieManagerService.getMoviesByLanguage(language);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@GetMapping("/country/{country}")
	public ResponseEntity<List<Movie>> getMoviesByCountry(@PathVariable String country) {
		List<Movie> movies = movieManagerService.getMoviesByCountry(country);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	@GetMapping("/rating/{rating}")
	public ResponseEntity<List<Movie>> getMoviesByRating(@PathVariable Double rating) {
		List<Movie> movies = movieManagerService.getMoviesByRating(rating);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
}
