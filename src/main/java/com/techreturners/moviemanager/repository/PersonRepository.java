package com.techreturners.moviemanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techreturners.moviemanager.model.Person;

@Repository
public interface PersonRepository  extends CrudRepository<Person, Long> {

}
