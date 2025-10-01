//package com.helloegor03.auth.config;
//
//import com.helloegor03.auth.model.UserDetailsImpl;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//import java.util.Map;
//
//import static java.security.KeyRep.Type.SECRET;
//
//@Component
//public class JwtUtil {
//    private final Key key;
//    private final long lifetime;
//
//    public JwtUtil(
//            @Value("${token.signing.key}") String secret,
//            @Value("${token.signing.lifetime}") long lifetime
//    ) {
//        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//        this.lifetime = lifetime;
//    }
//
//    public String generateToken(Authentication authentication) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("userId", userDetails.getId())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + lifetime))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String getUsernameFromToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJwt(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public String getUserIdFromToken(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJwt(token)
//                .getBody()
//                .get("userId", String.class);
//    }
//
//    public boolean validateJwtToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            System.out.println("JWT validation error: " + e.getMessage());
//        }
//        return false;
//    }
//}
