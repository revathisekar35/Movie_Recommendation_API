package com.techreturners.moviemanager.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.moviemanager.service.impl.MovieManagerServiceImpl;
import com.techreturners.moviemanager.model.Movie;

@AutoConfigureMockMvc
@SpringBootTest
public class MovieManagerControllerTest {
	@Mock
	private MovieManagerServiceImpl mockMovieManagerServiceImpl;

	@InjectMocks
	private MovieManagerController MovieManagerController;

	@Autowired
	private MockMvc mockMvcController;

	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		mockMvcController = MockMvcBuilders.standaloneSetup(MovieManagerController).build();
		mapper = new ObjectMapper();
	}

	@Test
	public void testGetAllMoviesReturnsMovies() throws Exception {

		List<Movie> Movies = new ArrayList<>();
		Movies.add(new Movie(1L, "Movie One", "This is the description for Movie One",
				(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()))));
		Movies.add(new Movie(2L, "Movie Two", "This is the description for Movie Two",
				(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()))));
		Movies.add(new Movie(3L, "Movie Three", "This is the description for Movie Three",
				(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()))));

		when(mockMovieManagerServiceImpl.getAllMovies()).thenReturn(Movies);

		this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/movie/getAllMovies"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Movie One"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Movie Two"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Movie Three"));
	}

	@Test
	public void testGetMappingGetMovieById() throws Exception {

		Movie movie = new Movie(4L, "Movie Four", "This is the description for Movie Four",
				(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())));

		when(mockMovieManagerServiceImpl.getMovieById(movie.getId())).thenReturn(movie);

		this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/movie/" + movie.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Movie Four"));
	}

	@Test
	public void testPostMappingAddAMovie() throws Exception {

		Movie movie = new Movie(4L, "Movie Four", "This is the description for Movie Four",
				(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())));

		when(mockMovieManagerServiceImpl.insertMovie(movie)).thenReturn(movie);

		this.mockMvcController
				.perform(MockMvcRequestBuilders.post("/api/movie/create/").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(movie)))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		verify(mockMovieManagerServiceImpl, times(1)).insertMovie(movie);
	}

	@Test
	public void testPutMappingUpdateAMovie() throws Exception {

		Movie movie = new Movie(4L, "Boss Baby", "This is the description for the Boss Baby",
				(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())));

		this.mockMvcController
				.perform(MockMvcRequestBuilders.put("/api/movie/update/" + movie.getId())
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(movie)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(mockMovieManagerServiceImpl, times(1)).updateMovieById(movie.getId(), movie);
	}

	@Test
	public void testDeleteMappingForDeleteAMovie() throws Exception {

		Movie movie = new Movie(4L, "Boss Baby", "This is the description for the Boss Baby",
				(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())));
		when(mockMovieManagerServiceImpl.insertMovie(movie)).thenReturn(movie);
		this.mockMvcController.perform(MockMvcRequestBuilders.delete("/api/movie/delete/" + movie.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		verify(mockMovieManagerServiceImpl, times(1)).deleteMovieById(movie.getId());
	}
}
