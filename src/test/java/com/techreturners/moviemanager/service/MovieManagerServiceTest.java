package com.techreturners.moviemanager.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.techreturners.moviemanager.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.techreturners.moviemanager.repository.MovieManagerRepository;
import com.techreturners.moviemanager.repository.PersonRepository;
import com.techreturners.moviemanager.service.impl.MovieManagerServiceImpl;

@DataJpaTest
public class MovieManagerServiceTest {

	@Mock
	private MovieManagerRepository mockMovieManagerRepository;
	
	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private MovieManagerServiceImpl movieManagerService;
	
	private final String DATE_FORMAT ="yyyy-MM-dd";

	@Test
	public void testGetAllMoviesReturnsListOfMovies() throws ParseException {

		List<Movie> movies = new ArrayList<>();
		Person person  = new Person(1L, "Tom", PersonRole.Actor);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		movies.add(new Movie(1L, "Movie One", "This is the description for Movie One",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		movies.add(new Movie(2L, "Movie Two", "This is the description for Movie Two",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		movies.add(new Movie(3L, "Movie Three", "This is the description for Movie Three",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));

		when(mockMovieManagerRepository.findAll()).thenReturn(movies);

		List<Movie> actualResult;
		actualResult = movieManagerService.getAllMovies();

		assertThat(actualResult).hasSize(3);
		assertThat(actualResult).isEqualTo(movies);
	}

	@Test
	public void testAddAMovie() throws ParseException {
		Person person  = new Person(1L, "Tom", PersonRole.Actor);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		var movie = new Movie(4L, "Movie Four", "This is the description for Movie Four",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList);

		when(mockMovieManagerRepository.save(movie)).thenReturn(movie);

		Movie actualResult = movieManagerService.insertMovie(movie);

		assertThat(actualResult).isEqualTo(movie);
	}

	@Test
	public void testGetMovieById() throws ParseException {
		Person person  = new Person(1L, "Tom", PersonRole.Actor);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		Long movieId = 5L;
		var movie = new Movie(5L, "Movie Five", "This is the description for Movie Five",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList);

		when(mockMovieManagerRepository.findById(movieId)).thenReturn(Optional.of(movie));

		Movie actualResult = movieManagerService.getMovieById(movieId);

		assertThat(actualResult).isEqualTo(movie);
	}

	@Test
	public void testUpdateMovieById() throws ParseException {
		Person person  = new Person(1L, "Tom", PersonRole.Actor);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);

		Long movieId = 5L;
		var movie = new Movie(5L, "Movie Five", "This is the description for Movie Five",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList);

		when(mockMovieManagerRepository.findById(movieId)).thenReturn(Optional.of(movie));
		when(mockMovieManagerRepository.save(movie)).thenReturn(movie);

		movieManagerService.updateMovieById(movieId, movie);

		verify(mockMovieManagerRepository, times(1)).save(movie);
	}
	
	@Test 
	public void testGetMoviesByActor() throws ParseException {
		List<Movie> movies = new ArrayList<>();
		Person person  = new Person(1L, "Tom", PersonRole.Actor);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		movies.add(new Movie(3L, "Movie Five", "This is the description for Movie Five",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		movies.add(new Movie(2L, "Movie Two", "This is the description for Movie Two",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		movies.add(new Movie(3L, "Movie Three", "This is the description for Movie Three",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		when(mockMovieManagerRepository.getMoviesByActor("Tom")).thenReturn(movies);
		List<Movie> actualResult = movieManagerService.getMoviesByActor("Tom");

		assertThat(actualResult).hasSize(3);
		assertThat(actualResult).isEqualTo(movies);
	}
	
	@Test 
	public void testGetMoviesByDirector() throws ParseException {
		List<Movie> movies = new ArrayList<>();
		Person person  = new Person(1L, "Tom", PersonRole.Director);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		movies.add(new Movie(3L, "Movie Five", "This is the description for Movie Five",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		movies.add(new Movie(2L, "Movie Two", "This is the description for Movie Two",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		movies.add(new Movie(3L, "Movie Three", "This is the description for Movie Three",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		when(mockMovieManagerRepository.getMoviesByDirector("Tom")).thenReturn(movies);
		List<Movie> actualResult = movieManagerService.getMoviesByDirector("Tom");

		assertThat(actualResult).hasSize(3);
		assertThat(actualResult).isEqualTo(movies);
	}

	@Test
	public void testGetMoviesByReleaseYear() throws ParseException {
		List<Movie> movies = new ArrayList<>();
		Person person  = new Person(1L, "Tom", PersonRole.Director);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		movies.add(new Movie(3L, "Movie Five", "This is the description for Movie Five",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		movies.add(new Movie(2L, "Movie Two", "This is the description for Movie Two",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		movies.add(new Movie(3L, "Movie Three", "This is the description for Movie Three",
				(new SimpleDateFormat(DATE_FORMAT).parse(LocalDate.now().toString())), Genre.Action, Certification.G, Language.English, Country.UK, personList));
		when(mockMovieManagerRepository.getMoviesByReleasedYear(2021)).thenReturn(movies);
		List<Movie> actualResult = movieManagerService.getMoviesByReleasedYear(2021);
		
		assertThat(actualResult).hasSize(3);
		assertThat(actualResult).isEqualTo(movies);
	}

}
