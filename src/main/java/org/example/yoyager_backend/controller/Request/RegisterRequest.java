package org.example.yoyager_backend.controller.Request;

import lombok.Getter;

@Getter
public class RegisterRequest {
  String email;
  String password;
  String name;
  String birthdate;
  String travel_style;
  String profile_image;
}
