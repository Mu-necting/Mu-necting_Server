package com.munecting.server.global.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


public class ApiKey {
    @Value("${spotify.client}")
    private String clientId;
    @Value("${spotify.secret}")
    private String clientSecret;
}
