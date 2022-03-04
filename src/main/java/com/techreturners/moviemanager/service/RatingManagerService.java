package com.techreturners.moviemanager.service;

import com.techreturners.moviemanager.exception.MovieNotFoungException;
import com.techreturners.moviemanager.model.Rating;

import java.util.List;

public interface RatingManagerService {

    List<Rating> getAllRatings();

    Rating getRatingById(Long Id);

    Rating insertRating(Rating rating) throws MovieNotFoungException;

    void updateRatingById(Long ratingId, Rating rating);

    void deleteRatingById(Long Id);
}
