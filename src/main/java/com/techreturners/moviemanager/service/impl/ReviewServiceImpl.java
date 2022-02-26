package com.techreturners.moviemanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.techreturners.moviemanager.model.Review;
import com.techreturners.moviemanager.repository.ReviewRepository;
import com.techreturners.moviemanager.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviewRepository.findAll().forEach(reviews::add);
        return reviews;
    }

    @Override
    public Review insertReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public void updateReviewById(Long id, Review review) {
        Review retrievedReview = reviewRepository.findById(id).get();
        retrievedReview.setComment(review.getComment());
        retrievedReview.setCreatedDate(review.getCreatedDate());
        reviewRepository.save(review);
    }

    @Override
    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }
}
