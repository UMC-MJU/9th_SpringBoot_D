package com.naho.umc9th.domain.common.jwt;

import com.naho.umc9th.domain.common.auth.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value( "${jwt.token.secretKey}") String secret,
            @Value( "${spring.jwt.token.expiration.access}") Long accessExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    // AccessToken 생성
    public String createAccessToken(CustomUserDetails user){
        return createToken(user, accessExpiration);
    }

    //토큰에서 이메일 가져오기
    public String getEmail(String token){
        try{
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e){
            return null;
        }
    }

    //토큰 유효성 확인
    public boolean isValid(String token){
        try{
            getClaims(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }

    //토큰 생성
    private String createToken(CustomUserDetails user, Duration expiration){
        Instant now = Instant.now();

        //인가 정보
        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", authorities)
                .claim("email", user.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiration)))
                .signWith(secretKey)
                .compact();
    }

    //토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException{
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }
}
