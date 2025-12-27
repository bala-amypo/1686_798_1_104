// // package com.example.demo.security;

// // import jakarta.servlet.FilterChain;
// // import jakarta.servlet.ServletException;
// // import jakarta.servlet.http.HttpServletRequest;
// // import jakarta.servlet.http.HttpServletResponse;

// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// // import org.springframework.stereotype.Component;
// // import org.springframework.web.filter.OncePerRequestFilter;

// // import java.io.IOException;
// // import java.util.Collections;

// // @Component
// // public class JwtAuthenticationFilter extends OncePerRequestFilter {

// //     private final JwtTokenProvider jwtTokenProvider;

// //     public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
// //         this.jwtTokenProvider = jwtTokenProvider;
// //     }

// //     @Override
// //     protected void doFilterInternal(
// //             HttpServletRequest request,
// //             HttpServletResponse response,
// //             FilterChain filterChain
// //     ) throws ServletException, IOException {

// //         String header = request.getHeader("Authorization");

// //         if (header != null && header.startsWith("Bearer ")) {
// //             String token = header.substring(7);

// //             if (jwtTokenProvider.validateToken(token)) {
// //                 String username = jwtTokenProvider.getUsername(token);

// //                 UsernamePasswordAuthenticationToken auth =
// //                         new UsernamePasswordAuthenticationToken(
// //                                 username, null, Collections.emptyList());

// //                 auth.setDetails(
// //                         new WebAuthenticationDetailsSource()
// //                                 .buildDetails(request));

// //                 SecurityContextHolder.getContext()
// //                         .setAuthentication(auth);
// //             }
// //         }

// //         filterChain.doFilter(request, response);
// //     }
// // }



// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtTokenProvider jwtTokenProvider;

//     public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain
//     ) throws ServletException, IOException {

//         String path = request.getServletPath();

//         // 🔥 MUST SKIP SWAGGER
//         if (path.startsWith("/swagger-ui")
//                 || path.startsWith("/v3/api-docs")
//                 || path.startsWith("/swagger-ui.html")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         // JWT logic can come later
//         filterChain.doFilter(request, response);
//     }
// }

package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            try {
                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);
                
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority(role))
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (JwtException e) {
            }
        }
        
        filterChain.doFilter(request, response);
    }
}