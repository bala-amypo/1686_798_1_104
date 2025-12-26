package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // REQUIRED for JwtTokenProvider(String, long)
    @Bean
    @Primary
    public String jwtSecretKey() {
        return "mysupersecretkeymysupersecretkey12345";
    }

    // REQUIRED for JwtTokenProvider(String, long)
    @Bean
    @Primary
    public Long jwtValidityInMilliseconds() {
        return 3600000L;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // ✅ ALLOW EVERYTHING ELSE (for now)
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
