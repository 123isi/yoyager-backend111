package org.example.yoyager_backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Entity
@Table(name="refreshToken")
@NoArgsConstructor
public class TokenEntity {
  @Id
  private Long id;

  private String token;

  @Builder
  public TokenEntity(Long id, String token) {
    this.id = id;
    this.token = token;
  }
}
