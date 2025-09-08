package org.example.yoyager_backend.global;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.example.yoyager_backend.domain.TokenEntity;
import org.example.yoyager_backend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
  private final TokenRepository tokenRepository;
  @Value("${jwt.secret-key}")
  private String secretKey;
  private static final long Time = 1000 * 60 * 60;
  private static final long LongTime = 1000 * 60 * 60 * 24 * 7;
  public String generateToken(Long id, String name, String email, String birthdate,String plan_type) {
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    return Jwts.builder()
            .claim("id", id)
            .claim("name", name)
            .claim("email", email)
            .claim("birthdate", birthdate)
            .claim("plan_type", plan_type)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + Time))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }
  public String generateRefreshToken(Long id, String name, String email, String birthdate,String plan_type) {
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    return Jwts.builder()
            .claim("id", id)
            .claim("name", name)
            .claim("email", email)
            .claim("birthdate", birthdate)
            .claim("plan_type", plan_type)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + LongTime))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  public Claims parseToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
            .build()
            .parseClaimsJws(token)
            .getBody();
  }
  public String getAccessToken(String ResfreshToken) {
    Claims Token = parseToken(ResfreshToken);
    Long id = Token.get("id", Long.class);
    String name = Token.get("name", String.class);
    String email = Token.get("email", String.class);
    String birthdate = Token.get("birthdate", String.class);
    String plan_type = Token.get("plan_type", String.class);
    TokenEntity entity = tokenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("토큰이 존재하지 않습니다."));
    return generateToken(id,name,email,birthdate,plan_type);
  }

}