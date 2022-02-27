package com.techreturners.moviemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techreturners.moviemanager.model.Movie;

@Repository
public interface MovieManagerRepository extends CrudRepository<Movie, Long> {

	@Query("select m from Movie m join m.person p where p.name = ?1 and p.role='0'")
	List<Movie> getMoviesByActor(String actorName);
	
	@Query("select m from Movie m join m.person p where p.name = ?1 and p.role='1'")
	List<Movie> getMoviesByDirector(String actorName);
}
