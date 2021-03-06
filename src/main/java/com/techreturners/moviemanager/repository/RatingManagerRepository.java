package com.techreturners.moviemanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techreturners.moviemanager.model.Rating;

@Repository
public interface RatingManagerRepository extends CrudRepository<Rating, Long> {

}
