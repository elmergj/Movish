package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.MediaType;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTitleRepository extends JpaRepository<TitleEntity, String> {

    Optional<TitleEntity> findByIdAndUserEntity_Id(String id, String userEntityId);

    Optional<TitleEntity> findByUserEntity_IdAndMediaIdAndAndMediaType(String userEntityId, String mediaId, MediaType mediaType);

    boolean existsByUserEntity_IdAndMediaIdAndMediaType(String userEntityId, String mediaId, MediaType mediaType);

}
