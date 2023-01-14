package com.user.service.services.implementation;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.externalservices.HotelService;
import com.user.service.repositories.UserResporitory;
import com.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserResporitory userResporitory;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {
//        int randomUserId = Integer.parseInt(UUID.randomUUID().toString());
//        user.setUserId(randomUserId);
        return this.userResporitory.save(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userResporitory.save(user);
    }

    @Override
    public User getUserById(Integer userId) {
        User user = this.userResporitory.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id " + userId + " is not found on server !!"));

        Rating[] ratingsOfUser = this.restTemplate.getForObject("http://RATING-SERVICE/rating/api/get/user/" + userId, Rating[].class);

        for(Rating rating:ratingsOfUser) {
//            Hotel hotel = this.restTemplate.getForObject("http://HOTEL-SERVICE/hotel/api/get/" + rating.getHotelId(), Hotel.class);

//            Fetching with the help of Feing Client
            ResponseEntity<Hotel> hotel = this.hotelService.getHotelById(rating.getHotelId());

            rating.setHotel(hotel.getBody());
        }

        user.setRatings(List.of(ratingsOfUser));

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userResporitory.findAll();

        for(User user:users) {
            Rating[] ratingsOfUser = this.restTemplate.getForObject("http://RATING-SERVICE/rating/api/get/user/" + user.getUserId(), Rating[].class);

            for(Rating rating:ratingsOfUser) {
//                Hotel hotel = this.restTemplate.getForObject("http://HOTEL-SERVICE/hotel/api/get/" + rating.getHotelId(), Hotel.class);

//                Fetching with the help of Feing Client
                ResponseEntity<Hotel> hotel = this.hotelService.getHotelById(rating.getHotelId());

                rating.setHotel(hotel.getBody());
            }

            user.setRatings(List.of(ratingsOfUser));
        }

        return users;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userResporitory.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id " + userId + " is not found on server !!"));

        this.userResporitory.delete(user);
    }
}
