package com.nr.chatapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt_secret}")
    private String jwtSecret;

    public String generateToken(String userName) {
        Map<String, String> claims = new HashMap<>();
        claims.put("userName", userName);
        return Jwts.builder().setClaims(claims).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60)).
                signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        System.out.println("----------------------------");
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.get("userName"));
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration : " + claims.getExpiration());
        System.out.println("Not Before : " + claims.getNotBefore());
        System.out.println("Audience :: " + claims.getAudience());
        return userDetails.getUsername().equals(claims.get("userName")) && !claims.getExpiration().before(new Date());

    }

    public Key getKey() {
        byte[] keyByte = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("userName");

    }
}
