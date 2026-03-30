package com.coffeeapp.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Λέει στο Spring Boot: "Διάβασε αυτό το αρχείο κατά την εκκίνηση, έχει ρυθμίσεις!"
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Ο κανόνας ισχύει για ΟΛΑ τα URL που ξεκινάνε από /api/
                // ΠΡΟΣΟΧΗ: Για Development επιτρέπουμε τα πάντα (*).
                // Στο ίντερνετ (Production) θα βάλουμε το ακριβές URL του Flutter App σου (π.χ. "https://my-app.com")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Επιτρεπόμενες κινήσεις
                .allowedHeaders("*"); // Επιτρέπουμε όλα τα headers (π.χ. JSON, Auth tokens)
    }
}