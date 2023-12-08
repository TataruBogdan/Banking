package com.intecbrussel.bankinguserservice.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {


    public CorsFilter corsConfig() {


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:8100");
        config.addAllowedOrigin("http://localhost:8200");
        config.addAllowedOrigin("http://localhost:8300");
        config.addAllowedOrigin("http://localhost:8400");
        config.addAllowedOrigin("http://localhost:8500");

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);

    }
}
