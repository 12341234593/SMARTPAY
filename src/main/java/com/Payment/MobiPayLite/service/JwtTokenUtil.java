package com.Payment.MobiPayLite.service;


import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;


import io.jsonwebtoken.Jwts.SIG;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

import static io.jsonwebtoken.Jwts.*;


@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "this_is_a_very_secret_key_123456"; // use env var in real app
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    public String generateToken(UserDetails userDetails) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return builder()
                .claims(Map.of("role", userDetails.getAuthorities().iterator().next().getAuthority()))
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SIG.HS256) // ✅ Latest way
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject(); // ✅ subject() is okay too
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        JwtParser parser = (JwtParser) Jwts.parser().setSigningKey(key).build();




        return (Claims) parser
                .parseSignedClaims(token).getPayload() ;
    }

}

