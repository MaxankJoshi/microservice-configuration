package com.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rating {
    private long ratingId;
    private int userId;
    private int hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
