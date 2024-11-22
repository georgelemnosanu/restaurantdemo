package com.restaurant.restaurantdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // În loc de setAllowedOrigins cu "*", folosim setAllowedOriginPatterns
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:[*]",       // Permite toate porturile localhost
                "https://*.lmncheap.store",   // Permite toate subdomeniile
                "https://lmncheap.store"
        ));

        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(Arrays.asList(
                "Origin",
                "Content-Type",
                "Accept",
                "Authorization",
                "X-Requested-With"
        ));

        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L); // Cache preflight pentru 1 oră

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}