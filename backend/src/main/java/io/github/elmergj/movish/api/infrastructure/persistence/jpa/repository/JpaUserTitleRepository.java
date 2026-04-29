package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserTitleRepository extends JpaRepository<UserTitleEntity, String> {

    Optional<UserTitleEntity> findByIdAndUserEntity_Id(String id, String userEntityId);
}
