package com.hotel.service.services.implementation;

import com.hotel.service.entities.Hotel;
import com.hotel.service.exceptions.ResourceNotFoundException;
import com.hotel.service.repositories.HotelRepository;
import com.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(Integer hotelId) {
        return this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id " + hotelId + " is not found on server !!"));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return this.hotelRepository.findAll();
    }

    @Override
    public void deleteHotel(Integer hotelId) {
        Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id " + hotelId + " is not found on server !!"));

        this.hotelRepository.delete(hotel);
    }
}
