package com.rating.service.controllers;

import com.rating.service.entities.Rating;
import com.rating.service.payloads.ApiResponse;
import com.rating.service.services.RatingService;
import com.rating.service.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating/api")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

//    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        rating.setRatingId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
        Rating createdRating = this.ratingService.createRating(rating);

        return new ResponseEntity<Rating>(createdRating, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
        Rating updatedRating = this.ratingService.updateRating(rating);

        return ResponseEntity.ok(updatedRating);
    }

    @GetMapping("/get/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable("ratingId") Long ratingId) {
        Rating rating = this.ratingService.getRatingById(ratingId);

        return ResponseEntity.ok(rating);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable("userId") Integer userId) {
        List<Rating> ratings = this.ratingService.getRatingsByUserId(userId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/get/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable("hotelId") Integer hotelId) {
        List<Rating> ratings = this.ratingService.getRatingsByHotelId(hotelId);

        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = this.ratingService.getAllRatings();

        return ResponseEntity.ok(ratings);
    }

    @DeleteMapping("/delete/{ratingId}")
    public ResponseEntity<ApiResponse> deleteRating(@PathVariable("ratingId") Long ratingId) {
        this.ratingService.deleteRating(ratingId);

        ApiResponse apiResponse = ApiResponse.builder().message("Rating is deleted successfully !!").success(true).httpStatus(HttpStatus.OK).build();

        return ResponseEntity.ok(apiResponse);
    }
}
