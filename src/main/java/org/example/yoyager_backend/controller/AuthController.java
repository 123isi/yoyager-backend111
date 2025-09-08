package org.example.yoyager_backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.yoyager_backend.controller.Request.LoginRequest;
import org.example.yoyager_backend.controller.Request.RegisterRequest;
import org.example.yoyager_backend.controller.Response.LoginResponse;
import org.example.yoyager_backend.controller.Response.RegisterResponse;
import org.example.yoyager_backend.service.LoginService;
import org.example.yoyager_backend.service.LogoutService;
import org.example.yoyager_backend.service.RefreshService;
import org.example.yoyager_backend.service.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final SignUpService signUpService;
  private final LoginService loginService;
  private final RefreshService refreshService;
  private final LogoutService logoutService;
  @PostMapping("/signup")
  public RegisterResponse signUp(@RequestBody RegisterRequest request) {
    return signUpService.signUp(request);
  }
  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest request) {return loginService.login(request);}
  @GetMapping("/refresh")
  public String refresh(@RequestHeader("refresh_token") String refreshToken) {return refreshService.refresh(refreshToken);}
  @DeleteMapping("/logout")
  public ResponseEntity<String> logout(@RequestHeader("access_token") String accessToken) {
    logoutService.logout(accessToken);
    return ResponseEntity.ok("삭제되었습니다");
  }
}
