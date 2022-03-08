package com.techreturners.moviemanager.repository;

import com.techreturners.moviemanager.model.Review;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewManagerRepository extends CrudRepository<Review, Long>{

	@Query("select r from Review r where r.movieId= ?1")
	List<Review> getReviewsByMovieId(Long movieId);

}
