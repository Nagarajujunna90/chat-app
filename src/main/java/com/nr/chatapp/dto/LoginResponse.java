package com.nr.chatapp.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String expiryTime;
    private String refreshToken;
    private String status;
    private String name;
    private String country;
}
