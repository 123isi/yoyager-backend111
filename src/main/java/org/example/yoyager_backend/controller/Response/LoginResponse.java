package org.example.yoyager_backend.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
  private String AccessToken;
  private String RefreshToken;
}
