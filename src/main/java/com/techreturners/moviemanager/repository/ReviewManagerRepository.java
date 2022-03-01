package com.techreturners.moviemanager.repository;

import com.techreturners.moviemanager.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewManagerRepository extends CrudRepository<Review, Long>{

}