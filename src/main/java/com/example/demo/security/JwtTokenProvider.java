

// // package com.example.demo.security;

// // import io.jsonwebtoken.*;
// // import java.util.Date;

// // public class JwtTokenProvider {

// //     private final String secret;
// //     private final long validityInMs;

// //     public JwtTokenProvider(String secret, long validityInMs) {
// //         this.secret = secret;
// //         this.validityInMs = validityInMs;
// //     }

// //     public String generateToken(String username) {
// //         return Jwts.builder()
// //                 .setSubject(username)
// //                 .setIssuedAt(new Date())
// //                 .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
// //                 .signWith(SignatureAlgorithm.HS256, secret)
// //                 .compact();
// //     }

// //     public boolean validateToken(String token) {
// //         try {
// //             Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
// //             return true;
// //         } catch (Exception e) {
// //             return false;
// //         }
// //     }

// //     public String getUsername(String token) {
// //         return Jwts.parser()
// //                 .setSigningKey(secret)
// //                 .parseClaimsJws(token)
// //                 .getBody()
// //                 .getSubject();
// //     }
// // }

package com.example.demo.security;

import com.example.demo.model.UserAccount;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider{
    private final long validityInMilliseconds;
    private final Key key;

    public JwtTokenProvider(String secretKey, long validityInMilliseconds) {
        this.validityInMilliseconds = validityInMilliseconds;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserAccount user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            if (token == null || token.isEmpty()) {
                return false;
            }
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}