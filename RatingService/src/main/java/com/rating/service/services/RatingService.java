package com.rating.service.services;

import com.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {
    public Rating createRating(Rating rating);
    public Rating updateRating(Rating rating);
    public Rating getRatingById(Long ratingId);
    public List<Rating> getRatingsByUserId(Integer userId);
    public List<Rating> getRatingsByHotelId(Integer hotelId);
    public List<Rating> getAllRatings();
    public void deleteRating(Long ratingId);
}
