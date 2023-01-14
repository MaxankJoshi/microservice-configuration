package com.rating.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "micro_ratings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rating {
    @Id
    @Field(name = "rating_Id")
    private long ratingId;

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Field(name = "user_id")
    private int userId;

    @Field(name = "hotel_id")
    private int hotelId;

    @Field(name = "rating")
    private int rating;

    @Field(name = "feedback")
    private String feedback;
}
