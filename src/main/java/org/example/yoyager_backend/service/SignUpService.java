package org.example.yoyager_backend.service;

import lombok.RequiredArgsConstructor;
import org.example.yoyager_backend.controller.Request.RegisterRequest;
import org.example.yoyager_backend.controller.Response.RegisterResponse;
import org.example.yoyager_backend.domain.UserEntity;
import org.example.yoyager_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public RegisterResponse signUp(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("이미 가입된 이메일입니다.");
    }
    UserEntity user = UserEntity.builder()
            .request(request)
            .passwordEncoder(passwordEncoder)
            .build();
    userRepository.save(user);
    return RegisterResponse.from(user);
  }
}
