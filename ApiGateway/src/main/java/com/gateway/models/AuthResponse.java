package com.gateway.models;

import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;

    private long expireAt;
    private Collection<String> authorities;
}
