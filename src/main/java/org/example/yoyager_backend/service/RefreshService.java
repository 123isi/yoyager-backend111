package org.example.yoyager_backend.service;


import lombok.RequiredArgsConstructor;
import org.example.yoyager_backend.domain.TokenEntity;
import org.example.yoyager_backend.global.JwtProvider;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshService {
  private final JwtProvider jwtProvider;

  public String refresh(String refreshToken) {
    return jwtProvider.getAccessToken(refreshToken);
  }
}
