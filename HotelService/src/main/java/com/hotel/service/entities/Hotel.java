package com.hotel.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "micro_hotels")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hotelId;

    @Column(name = "name", nullable = false, length = 20)
    private String hotelName;

    @Column(name = "location", nullable = false, length = 100)
    private String hotelLocation;

    @Column(name = "about", nullable = false, length = 200)
    private String hotelAbout;
}
