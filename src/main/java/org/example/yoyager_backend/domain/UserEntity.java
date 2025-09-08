package org.example.yoyager_backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.yoyager_backend.controller.Request.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;



@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;
  private String birthdate;
  private String travel_style;

  @Column(columnDefinition = "TEXT")
  private String profile_image;

  private String plan_type;

  @Builder
  public UserEntity(RegisterRequest request, PasswordEncoder passwordEncoder) {
    this.email = request.getEmail();
    this.password = passwordEncoder.encode(request.getPassword());
    this.name = request.getName();
    this.birthdate = request.getBirthdate();
    this.travel_style = request.getTravel_style();
    this.profile_image = request.getProfile_image();
    this.plan_type = "nonpaid";
  }
}
