package com.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "name", nullable = false, length = 20)
    private String userName;

    @Column(name = "email", nullable = false, length = 30)
    private String userEmail;

    @Column(name = "about", nullable = false, length = 200)
    private String userAbout;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
