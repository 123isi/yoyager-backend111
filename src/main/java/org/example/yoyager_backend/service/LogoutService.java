package org.example.yoyager_backend.service;


import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.example.yoyager_backend.global.JwtProvider;
import org.example.yoyager_backend.repository.TokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogoutService {
  private final TokenRepository tokenRepository;
  private final JwtProvider jwtProvider;

  public void logout(String accessToken) {
    Claims token = jwtProvider.parseToken(accessToken);
    Long id = token.get("id", Long.class);
    tokenRepository.deleteById(id);
  }
}
