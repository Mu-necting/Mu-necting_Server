package com.munecting.server.global.config.secure.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Cors error 설정
@Configuration
@Component
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS" , "PATCH") // Http Method 들 하용
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "atk"); // 응답헤더에 atk 이라는 이름의 토큰 포함
    }
}