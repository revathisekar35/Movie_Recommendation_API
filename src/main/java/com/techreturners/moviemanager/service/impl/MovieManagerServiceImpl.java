package com.techreturners.moviemanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.techreturners.moviemanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techreturners.moviemanager.repository.MovieManagerRepository;
import com.techreturners.moviemanager.repository.PersonRepository;
import com.techreturners.moviemanager.service.MovieManagerService;

@Service
public class MovieManagerServiceImpl implements MovieManagerService {

	@Autowired
	MovieManagerRepository movieManagerRepository;

	@Autowired
	PersonRepository personRepository;

	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<>();
		movieManagerRepository.findAll().forEach(movies::add);
		return movies;
	}

	@Override
	public Movie insertMovie(Movie movie) {
		return insertpeople(movie);
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
		retrievedMovie.setReleaseDate(movie.getReleaseDate());
		retrievedMovie.setPerson(movie.getPerson());
		movieManagerRepository.save(retrievedMovie);
	}

	@Override
	public void deleteMovieById(Long id) {
		movieManagerRepository.deleteById(id);
	}

	@Override
	public List<Movie> getMoviesByActor(String actorName) {
		return movieManagerRepository.getMoviesByActor(actorName);
	}

	@Override
	public List<Movie> getMoviesByDirector(String directorName) {
		return movieManagerRepository.getMoviesByDirector(directorName);
	}

	@Override
	public List<Movie> getMoviesByReleasedYear(int year) {
		return movieManagerRepository.getMoviesByReleasedYear(year);
	}
	
	@Override
	public List<Person> getMovieCrewList() {
		List<Person> person = new ArrayList<>();
		personRepository.findAll().forEach(person::add);
		return person;
	}

	@Override
	public List<Movie> getMoviesByGenre(Genre genre) {
		return movieManagerRepository.getMoviesByGenre(genre);
	}

	@Override
	public List<Movie> getMoviesByCertification(Certification certification) {
		return movieManagerRepository.getMoviesByCertification(certification);
	}

	@Override
	public List<Movie> getMoviesByLanguage(Language language) {
		return movieManagerRepository.getMoviesByLanguage(language);
	}

	@Override
	public List<Movie> getMoviesByCountry(Country country) {
		return movieManagerRepository.getMoviesByCountry(country);
	}


	private Movie insertpeople(Movie movie) {
		if (movie.getId() == null) {
			List<Person> personList = new ArrayList<Person>();
			for (Person person : movie.getPerson()) {
				personList.add(personRepository.save(person));
			}
			movie.setPerson(personList);
			movieManagerRepository.save(movie);
		}
		return movie;
	}
}
