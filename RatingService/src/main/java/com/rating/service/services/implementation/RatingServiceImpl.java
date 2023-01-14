package com.rating.service.services.implementation;

import com.rating.service.entities.Rating;
import com.rating.service.exceptions.ResourceNotFoundException;
import com.rating.service.repositories.RatingRepository;
import com.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingById(Long ratingId) {
        return this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with given rating id " + ratingId + " is not found on server !!"));
    }

    @Override
    public List<Rating> getRatingsByUserId(Integer userId) {
        List<Rating> ratings = this.ratingRepository.findByUserId(userId);

        if(ratings == null) {
            throw new ResourceNotFoundException("Ratings with given user id " + userId + " is not found on server !!");
        }

        return ratings;
    }

    @Override
    public List<Rating> getRatingsByHotelId(Integer hotelId) {
        List<Rating> ratings = this.ratingRepository.findByHotelId(hotelId);

        if(ratings == null) {
            throw new ResourceNotFoundException("Rating with given hotel id " + hotelId + " is not found on server !!");
        }

        return ratings;
    }

    @Override
    public List<Rating> getAllRatings() {
        return this.ratingRepository.findAll();
    }

    @Override
    public void deleteRating(Long ratingId) {
        Rating rating = this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with given id " + ratingId + " is not found on server !!"));

        this.ratingRepository.delete(rating);
    }
}
