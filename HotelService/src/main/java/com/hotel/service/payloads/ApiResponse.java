package com.hotel.service.payloads;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;
}
