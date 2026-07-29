package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTitleRepository extends JpaRepository<TitleEntity, String> {

    Optional<TitleEntity> findByIdAndUserEntity_Id(String id, String userEntityId);

    Optional<TitleEntity> findByMediaIdAndUserEntity_Id(String mediaId, String userEntityId);

    boolean existsByMediaIdAndUserEntity_Id(String mediaId, String userEntityId);

    boolean existsByE (String mediaId, String userEntityId);
}
