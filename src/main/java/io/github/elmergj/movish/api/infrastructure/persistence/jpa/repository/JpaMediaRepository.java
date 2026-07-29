package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMediaRepository extends JpaRepository<MediaEntity, String> {

    Optional<MediaEntity> findByIdAndMediaType(String id, MediaType mediaType);
}
