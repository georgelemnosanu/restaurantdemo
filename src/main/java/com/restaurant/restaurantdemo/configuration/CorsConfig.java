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

        // Permite toate pattern-urile necesare
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:[*]",       // Pentru dezvoltare locală
                "https://localhost:[*]",
                "https://lmncheap.store",
                "https://*.lmncheap.store",   // Pentru domeniul principal și subdomenii
                "capacitor://*",              // Pentru Capacitor în aplicații mobile
                "ionic://*",                  // Pentru Ionic
                "http://*",                   // Pentru diverse medii de dezvoltare
                "https://*"                   // Pentru producție
        ));

        // Permite toate metodele HTTP necesare
        corsConfiguration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD"
        ));

        // Permite toate header-ele comune
        corsConfiguration.setAllowedHeaders(Arrays.asList(
                "Origin",
                "Content-Type",
                "Accept",
                "Authorization",
                "X-Requested-With",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
        ));

        // Permite expunerea anumitor headere către client
        corsConfiguration.setExposedHeaders(Arrays.asList(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials",
                "Authorization"
        ));

        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L); // Cache preflight pentru 1 oră

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}