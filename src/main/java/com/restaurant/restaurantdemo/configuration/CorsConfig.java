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

        // Permite toate originile
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        // SAU, dacă ai nevoie de credentials, specifică explicit originile:
        // corsConfiguration.setAllowedOrigins(Arrays.asList(
        //     "http://localhost:3000",
        //     "https://lmncheap.store",
        //     "https://www.lmncheap.store"
        // ));

        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        // Dacă setezi AllowedOrigins ca "*", nu poți folosi allowCredentials
        // Dacă ai nevoie de credentials, trebuie să specifici explicit originile
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
