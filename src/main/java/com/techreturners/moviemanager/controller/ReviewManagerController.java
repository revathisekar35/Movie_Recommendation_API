package com.techreturners.moviemanager.controller;

import com.techreturners.moviemanager.model.Review;
import com.techreturners.moviemanager.service.ReviewManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewManagerController {

    @Autowired
    private ReviewManagerService reviewManagerService;

    @GetMapping("/getAllReviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewManagerService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping({ "/{reviewId}" })
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewManagerService.getReviewById(reviewId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review newReview = reviewManagerService.insertReview(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @PutMapping({"/update/{reviewId}"})
    public ResponseEntity<Review> updateReviewById(@PathVariable("reviewId") Long reviewId, @RequestBody Review review) {
        reviewManagerService.updateReviewById(reviewId, review);
        return new ResponseEntity<>(reviewManagerService.getReviewById(reviewId), HttpStatus.OK);
    }

    @DeleteMapping({"/delete/{reviewId}"})
    public ResponseEntity<Review> deleteReviewById(@PathVariable Long reviewId) {
        reviewManagerService.deleteReviewById(reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping({"/movie/{movieId}"})
    public ResponseEntity<List<Review>> getReviewByMovieId(@PathVariable Long movieId) {
    	 List<Review> reviews = reviewManagerService.getReviewsByMovieId(movieId);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

}

