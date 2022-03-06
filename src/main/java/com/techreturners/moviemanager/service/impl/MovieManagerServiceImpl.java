package com.techreturners.moviemanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.techreturners.moviemanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techreturners.moviemanager.config.AppConfig;
import com.techreturners.moviemanager.config.TwilioInitilizer;
import com.techreturners.moviemanager.model.Movie;
import com.techreturners.moviemanager.model.Person;
import com.techreturners.moviemanager.repository.MovieManagerRepository;
import com.techreturners.moviemanager.repository.PersonRepository;
import com.techreturners.moviemanager.service.MovieManagerService;

@Service
public class MovieManagerServiceImpl implements MovieManagerService {

	@Autowired
	MovieManagerRepository movieManagerRepository;

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	AppConfig appconfig;
	
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
	public Movie updateMovieById(Long id, Movie movie) {
		Movie retrievedMovie = movieManagerRepository.findById(id).get();
		retrievedMovie.setName(movie.getName());
		retrievedMovie.setDescription(movie.getDescription());
		retrievedMovie.setReleaseDate(movie.getReleaseDate());
		retrievedMovie.setPerson(movie.getPerson());
		retrievedMovie.setCountry(movie.getCountry());
		retrievedMovie.setGenre(movie.getGenre());
		retrievedMovie.setCertification(movie.getCertification());
		retrievedMovie.setLanguage(movie.getLanguage());
		return movieManagerRepository.save(retrievedMovie);
	}

	@Override
	public void deleteMovieById(Long id) {
			Movie movie = movieManagerRepository.findById(id).get();
			movie.getPerson().removeAll(movie.getPerson());
			movieManagerRepository.delete(movie);
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
	public List<Movie> getMoviesByGenre(String genre) {
		Genre.valueOf(Genre.class, genre).ordinal();
		return movieManagerRepository.getMoviesByGenre(genre);
	}

	@Override
	public List<Movie> getMoviesByCertification(String certification) {
		Certification.valueOf(Certification.class, certification).ordinal();
		return movieManagerRepository.getMoviesByCertification(certification);
	}

	@Override
	public List<Movie> getMoviesByLanguage(String language) {
		Language.valueOf(Language.class, language).ordinal();
		return movieManagerRepository.getMoviesByLanguage(language);
	}

	@Override
	public List<Movie> getMoviesByCountry(String country) {
		Country.valueOf(Country.class, country).ordinal();
		return movieManagerRepository.getMoviesByCountry(country);
	}
	private Movie insertpeople(Movie movie) {
			List<Person> personList = new ArrayList<Person>();
			for (Person person : movie.getPerson()) {
				personList.add(personRepository.save(person));
			}
			movie.setPerson(personList);
			movieManagerRepository.save(movie);
		return movie;
	}
}
