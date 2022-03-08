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
	
	@Query("from Movie  where year(releaseDate)= ?1")
	List<Movie> getMoviesByReleasedYear(int year);

	@Query("select m from Movie m where genre = '?1'")
	List<Movie> getMoviesByGenre(int genre);

	@Query("select m from Movie m where certification = '?1'")
	List<Movie> getMoviesByCertification(int certification);

	@Query("select m from Movie m where language = '?1'")
	List<Movie> getMoviesByLanguage(int language);

	@Query("select m from Movie m where country = '?1'")
	List<Movie> getMoviesByCountry(int country);
	
	@Query("select r.movie from Rating r where r.totalRating= ?1")
	List<Movie> getMoviesByRating(double rating);
	
	
}
