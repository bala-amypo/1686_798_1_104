// package com.example.demo.config;

// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class JwtConfig {

//     @Bean
//     public JwtTokenProvider jwtTokenProvider(
//             @Value("${jwt.secret}") String secret,
//             @Value("${jwt.validity}") long validityInMs) {

//         return new JwtTokenProvider(secret, validityInMs);
//     }
// }


// package com.example.demo.config;

// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class JwtConfig {

//     @Bean
//     public JwtTokenProvider jwtTokenProvider(
//             @Value("${jwt.secret}") String secret,
//             @Value("${jwt.validity}") long validityInMs) {

//         return new JwtTokenProvider(secret, validityInMs);
//     }
// }
