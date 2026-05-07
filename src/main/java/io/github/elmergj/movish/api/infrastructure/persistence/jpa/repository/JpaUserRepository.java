package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByAuthId(String authId);

    boolean existsByAuthId(String authId);
}
