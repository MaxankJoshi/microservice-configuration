package com.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hotel {
    private int hotelId;
    private String hotelName;
    private String hotelLocation;
    private String hotelAbout;
}
