package com.techreturners.moviemanager.service.impl;

import com.techreturners.moviemanager.model.Review;
import com.techreturners.moviemanager.repository.ReviewManagerRepository;
import com.techreturners.moviemanager.service.ReviewManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewManagerServiceImpl implements ReviewManagerService {

    @Autowired
    ReviewManagerRepository reviewManagerRepository;

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviewManagerRepository.findAll().forEach(reviews::add);
        return reviews;
    }

    @Override
    public Review insertReview(Review review) {
        return reviewManagerRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewManagerRepository.findById(id).get();
    }

    @Override
    public void updateReviewById(Long id, Review review) {
        Review retrievedReview = reviewManagerRepository.findById(id).get();
        retrievedReview.setComment(review.getComment());
        retrievedReview.setCreatedDate(review.getCreatedDate());
        reviewManagerRepository.save(review);
    }

    @Override
    public void deleteReviewById(Long id) {
        reviewManagerRepository.deleteById(id);
    }
    
    @Override
    public List<Review> getReviewsByMovieId(Long movieId){
    	return reviewManagerRepository.getReviewsByMovieId(movieId);
    }

}
