package com.rating.service.repositories;

import com.rating.service.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,Long> {
    public List<Rating> findByUserId(Integer userId);
    public List<Rating> findByHotelId(Integer hotelId);
}
