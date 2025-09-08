package org.example.yoyager_backend.controller.Request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {
  String email;
  String password;
}
