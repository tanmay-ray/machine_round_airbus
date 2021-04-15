package com.challenge.util;

import com.challenge.dto.UserAuthRequestDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    private JwtUtil() {}

    private static String SECRET_KEY = "secret";

    public static String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    public static String generateToken(UserAuthRequestDTO userAuthRequestDTO) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userAuthRequestDTO.getEmail());
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static Boolean validateToken(String token, UserAuthRequestDTO userAuthRequestDTO) {
        final String username = extractEmail(token);
        return username.equals(userAuthRequestDTO.getEmail());
    }
}
