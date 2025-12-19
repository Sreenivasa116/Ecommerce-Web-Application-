//package com.application;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//
//import java.util.Date;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtUtils {
//
/// /    private final String SECURITY_KEY  = "209E1A1224SREENIVASA181812166@11062001";
//    @Value("${jwt.secret-key}")
//    private String SECURITY_KEY;
//
//
//    private SecretKey getSecretkey(){
//        return Keys.hmacShaKeyFor
//                (SECURITY_KEY.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public String createJwtToken(Map<String,Object> claims, String email){
//        long expTime = 1000*60*60*6;
//        return Jwts.builder()
//                .claims()
//                .add(claims)
//                .subject(email)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis()+expTime))
//                .and()
//                .signWith(getSecretkey())
//                .compact();
//    }
//
//    public Claims extractAllClaims(String token){
//        return Jwts.parser()
//                .verifyWith(getSecretkey())
//                .build()
//                .parseClaimsJws(token)
//                .getPayload();
//    }
//
//    public <T> T extractClaim(String token,Function<Claims,T> claimsResolver){
//        final Claims payLoad = extractAllClaims(token);
//        return claimsResolver.apply(payLoad);
//    }
//
//    public String extractUserEmail(String token){
//        return extractClaim(token, Claims :: getSubject);
//    }
//
//    public  Date extractExpDate(String token){
//        return extractClaim(token,Claims :: getExpiration);
//    }
//
//    public boolean isTokenExpired(String token){
//        Date date = extractExpDate(token);
//        return  date.before(new Date());
//    }
//
//    public boolean validateToken(String token,String email){
//       final String extractedEmail = extractUserEmail(token);
//       return (extractedEmail.equals(email) && !isTokenExpired(token));
//    }
//
//}
