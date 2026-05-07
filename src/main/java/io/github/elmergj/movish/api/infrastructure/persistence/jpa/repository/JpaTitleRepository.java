package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTitleRepository extends JpaRepository<TitleEntity, String> {

    Optional<TitleEntity> findByIdAndMediaType(String id, MediaType mediaType);
}
