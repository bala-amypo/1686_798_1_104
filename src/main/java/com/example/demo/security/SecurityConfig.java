// package com.example.demo.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//             .authorizeHttpRequests().anyRequest().permitAll();
//         return http.build();
//     }
// }


// package com.example.demo.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             // Disable CSRF for APIs & Swagger
//             .csrf(csrf -> csrf.disable())

//             // Allow Swagger and all endpoints
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                     "/v3/api-docs/**",
//                     "/swagger-ui/**",
//                     "/swagger-ui.html"
//                 ).permitAll()
//                 .anyRequest().permitAll()
//             );

//         return http.build();
//     }
// }



package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // 1. Disable CSRF (essential for testing APIs with Postman/Swagger)
            .csrf(csrf -> csrf.disable())

            // 2. Configure request permissions
            .authorizeHttpRequests(auth -> auth
                // Explicitly allow all Swagger-related static resources and API docs
                .requestMatchers(
                    "/v3/api-docs/**",      // OpenAPI JSON
                    "/v3/api-docs.yaml",    // OpenAPI YAML
                    "/swagger-ui/**",       // Swagger UI HTML and JS
                    "/swagger-ui.html",     // Swagger UI entry point
                    "/swagger-resources/**",// Swagger config resources
                    "/webjars/**"           // IMPORTANT: JS/CSS for the UI
                ).permitAll()
                
                // Allow your Auth endpoints
                .requestMatchers("/api/auth/**").permitAll()
                
                // Since you are in development/test mode, permit everything else for now
                .anyRequest().permitAll()
            )
            
            // 3. Optional: Allow H2 Console if you are using it (frames must be sameOrigin)
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}