package com.techreturners.moviemanager.controller;

import com.techreturners.moviemanager.model.Rating;
import com.techreturners.moviemanager.service.RatingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingManagerController {

	@Autowired
	private RatingManagerService ratingManagerService;

	@GetMapping("/getAllRatings")
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> ratings = ratingManagerService.getAllRatings();
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}

	@GetMapping({ "/{ratingId}" })
	public ResponseEntity<Rating> getRatingById(@PathVariable Long ratingId) {
		return new ResponseEntity<>(ratingManagerService.getRatingById(ratingId), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Rating> addRating(@RequestBody Rating rating) throws Exception {
		Rating newRating = ratingManagerService.insertRating(rating);
		return new ResponseEntity<>(newRating, HttpStatus.CREATED);
	}

	@PutMapping({ "/update/{ratingId}" })
	public ResponseEntity<Rating> updateRatingById(@PathVariable("ratingId") Long ratingId, @RequestBody Rating rating) {
		ratingManagerService.updateRatingById(ratingId, rating);
		return new ResponseEntity<>(ratingManagerService.getRatingById(ratingId), HttpStatus.OK);
	}

	@DeleteMapping({ "/delete/{ratingId}" })
	public ResponseEntity<Rating> deleteRatingById(@PathVariable Long ratingId) {
		ratingManagerService.deleteRatingById(ratingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
