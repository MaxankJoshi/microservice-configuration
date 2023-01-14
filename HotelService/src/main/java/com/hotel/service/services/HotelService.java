package com.hotel.service.services;

import com.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel createHotel(Hotel hotel);
    public Hotel updateHotel(Hotel hotel);
    public Hotel getHotelById(Integer hotelId);
    public List<Hotel> getAllHotels();
    public void deleteHotel(Integer hotelId);
}
