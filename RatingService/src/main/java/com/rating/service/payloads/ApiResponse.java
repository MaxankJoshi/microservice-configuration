package com.rating.service.payloads;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;
}
