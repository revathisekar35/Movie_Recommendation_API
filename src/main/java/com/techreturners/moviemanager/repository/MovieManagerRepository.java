package com.techreturners.moviemanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techreturners.moviemanager.model.Movie;

@Repository
public interface MovieManagerRepository extends CrudRepository<Movie, Long> {

}
