package com.hotel.service.controllers;

import com.hotel.service.entities.Hotel;
import com.hotel.service.payloads.ApiResponse;
import com.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/api")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel createdHotel = this.hotelService.createHotel(hotel);

        return new ResponseEntity<Hotel>(createdHotel,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        Hotel updatedHotel = this.hotelService.updateHotel(hotel);

        return ResponseEntity.ok(updatedHotel);
    }

    @GetMapping("/get/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") Integer hotelId) {
        Hotel hotel = this.hotelService.getHotelById(hotelId);

        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/getAll")
    private ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = this.hotelService.getAllHotels();

        return ResponseEntity.ok(hotels);
    }

    @DeleteMapping("/delete/{hotelId}")
    private ResponseEntity<ApiResponse> deleteHotel(@PathVariable("hotelId") Integer hotelId) {
        this.hotelService.deleteHotel(hotelId);

        ApiResponse apiResponse = ApiResponse.builder().message("Hotel is deleted successfully with hotel id: " + hotelId).success(true).httpStatus(HttpStatus.OK).build();

        return ResponseEntity.ok(apiResponse);
    }
}
