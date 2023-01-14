package com.user.service.externalservices;

import com.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("hotel/api/get/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") Integer hotelId);
}
