package com.techreturners.moviemanager.service.impl;

import com.techreturners.moviemanager.model.Rating;
import com.techreturners.moviemanager.repository.RatingManagerRepository;
import com.techreturners.moviemanager.service.RatingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingManagerServiceImpl implements RatingManagerService {

    @Autowired
    RatingManagerRepository ratingManagerRepository;

    @Override
    public List<Rating> getAllRatings() {
        List<Rating> ratings = new ArrayList<>();
        ratingManagerRepository.findAll().forEach(ratings::add);
        return ratings;
    }

    @Override
    public Rating getRatingById(Long Id) {
        return ratingManagerRepository.findById(Id).get();
    }

    @Override
    public Rating insertRating(Rating rating) {
        return ratingManagerRepository.save(rating);
    }

    @Override
    public void updateRatingById(Long id, Rating rating) {
        Rating retrievedRating = ratingManagerRepository.findById(id).get();
        retrievedRating.setLikes(rating.getLikes());
        retrievedRating.setDislikes(rating.getDislikes());
        retrievedRating.setTotalRating(rating.getTotalRating());
        ratingManagerRepository.save(rating);
    }

    @Override
    public void deleteRatingById(Long Id) {
        ratingManagerRepository.deleteById(Id);
    }
}
