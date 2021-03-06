package com.techreturners.moviemanager.repository;

import com.techreturners.moviemanager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserManagerRepository extends CrudRepository<User, Long>{
}
