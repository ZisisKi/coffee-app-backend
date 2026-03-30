package com.coffeeapp.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Επιτρέπουμε όλα τα URL (π.χ. /api/orders)
                .allowedOrigins("*") // Επιτρέπουμε σε οποιοδήποτε frontend να μας μιλήσει
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Όλες οι εντολές
                .allowedHeaders("*"); // Όλα τα headers
    }
}