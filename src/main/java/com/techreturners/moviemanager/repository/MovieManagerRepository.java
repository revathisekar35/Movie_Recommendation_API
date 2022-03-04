package com.techreturners.moviemanager.repository;

import java.util.List;

import com.techreturners.moviemanager.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieManagerRepository extends CrudRepository<Movie, Long> {

	@Query("select m from Movie m join m.person p where p.name = ?1 and p.role='0'")
	List<Movie> getMoviesByActor(String actorName);
	
	@Query("select m from Movie m join m.person p where p.name = ?1 and p.role='1'")
	List<Movie> getMoviesByDirector(String actorName);
	
	@Query("from Movie  where year(releaseDate)= ?1")
	List<Movie> getMoviesByReleasedYear(int year);

	@Query("select m from Movie m where genre = ?1 ")
	List<Movie> getMoviesByGenre(String genre);

	@Query("select m from Movie m where certification = ?1 ")
	List<Movie> getMoviesByCertification(String certification);

	@Query("select m from Movie m where language = ?1 ")
	List<Movie> getMoviesByLanguage(String language);

	@Query("select m from Movie m where country = ?1 ")
	List<Movie> getMoviesByCountry(String country);
}
