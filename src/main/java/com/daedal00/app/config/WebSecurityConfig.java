package com.daedal00.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // Allow all OPTIONS requests
                    .requestMatchers("/api/users/register", "/api/users/login").permitAll()  // Allow registration and login without authentication
                    .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());  // Enable Basic Authentication
        return http.build();
    }
}
