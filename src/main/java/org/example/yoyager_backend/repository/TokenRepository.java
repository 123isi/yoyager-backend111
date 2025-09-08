package org.example.yoyager_backend.repository;

import org.example.yoyager_backend.domain.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

}
